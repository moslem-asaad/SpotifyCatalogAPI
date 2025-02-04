//package com.example.catalog.interceptors;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.util.Arrays;
//import java.util.concurrent.ConcurrentHashMap;
//
//
//@Component
//public class RateLimit implements HandlerInterceptor {
//
//    @Value("${rate-limit.algo}")
//    private String rateLimitAlgo;
//
//    @Value("${rate-limit.rpm}")
//    private String rateLimitRPM;
//
//    public String getRateLimitAlgo() {
//        return rateLimitAlgo;
//    }
//
//    public String getRateLimitRPM() {
//        return rateLimitRPM;
//    }
//
//    private ConcurrentHashMap<String, Object> requestMap = new ConcurrentHashMap<>();
//
//    public void setRateLimitAlgo(String rateLimitAlgo){
//        this.rateLimitAlgo = rateLimitAlgo;
//    }
//
//    public void setRateLimitRPM(String rateLimitRPM) {
//        this.rateLimitRPM = rateLimitRPM;
//    }
//    public void clear() {
//        requestMap.clear();
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String clientIp = request.getRemoteAddr();
//        String requestURI = request.getRequestURI();
//
//        if (requestURI.equals("/internal")){
//            return true;
//        }
//
//        if (!isAllowed(clientIp)) {
//            response.setStatus(429);
//            response.setHeader("X-Rate-Limit-Remaining", "0");
//            response.setHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(getRetryAfterSeconds(clientIp)));
//            return false;
//        }
//
//        response.setHeader("X-Rate-Limit-Remaining", String.valueOf(getNumberOfRemainingRequests(clientIp)));
//
//        /*if("moving".equalsIgnoreCase(rateLimitAlgo)){
//            CircularBuffer circularBuffer = (CircularBuffer) requestMap.get(clientIp);
//            System.out.println(circularBuffer.toString());
//        }
//        */
//
//        return true;
//    }
//
//
//
//
//    private boolean isAllowed(String clientIp) {
//        if ("moving".equalsIgnoreCase(rateLimitAlgo)) {
//            return isAllowedSliding(clientIp);
//        } else if ("fixed".equalsIgnoreCase(rateLimitAlgo)) {
//            return isAllowedFixed(clientIp);
//        } else {
//            throw new IllegalArgumentException("Unknown rate limiting algorithm: " + rateLimitAlgo);
//        }
//    }
//
//    private synchronized boolean isAllowedSliding(String clientIp){
//        long currTime = System.currentTimeMillis();
//        requestMap.putIfAbsent(clientIp, new CircularBuffer(Integer.parseInt(rateLimitRPM)));
//        CircularBuffer circularBuffer = (CircularBuffer) requestMap.get(clientIp);
//        return circularBuffer.add(currTime);
//    }
//
//    private synchronized boolean isAllowedFixed(String clientIp){
//        long currTime = System.currentTimeMillis();
//        long minMills = 60000;
//        requestMap.putIfAbsent(clientIp, new FixedInterval(currTime,0));
//        FixedInterval fixedInterval = (FixedInterval) requestMap.get(clientIp);
//
//        if(currTime - fixedInterval.startTime >= minMills){
//            fixedInterval.requestCount = 1;
//            fixedInterval.startTime = currTime;
//            return true;
//        }else{
//            if(fixedInterval.requestCount < Integer.parseInt(rateLimitRPM)){
//                fixedInterval.requestCount++;
//                return true;
//            }
//            else return false;
//        }
//    }
//
//    private int getNumberOfRemainingRequests(String clientIp) {
//        if ("moving".equalsIgnoreCase(rateLimitAlgo)) {
//            return calculateRemainingSliding(clientIp);
//        } else if ("fixed".equalsIgnoreCase(rateLimitAlgo)) {
//            return calculateRemainingFixed(clientIp);
//        } else {
//            throw new IllegalArgumentException("Unknown rate limiting algorithm: " + rateLimitAlgo);
//        }
//    }
//
//    private int calculateRemainingFixed(String clientIp) {
//        FixedInterval fixedInterval = (FixedInterval) requestMap.get(clientIp);
//        if (fixedInterval == null) return Integer.parseInt(rateLimitRPM);
//
//        return Math.max(0,Integer.parseInt(rateLimitRPM) - fixedInterval.requestCount);
//    }
//
//    private int calculateRemainingSliding(String clientIp) {
//        CircularBuffer circularBuffer = (CircularBuffer) requestMap.get(clientIp);
//        if (circularBuffer == null) return Integer.parseInt(rateLimitRPM);
//        long currTime = System.currentTimeMillis();
//        int requestInWindow = 0;
//
//        for(long element: circularBuffer.buffer){
//            if(element > 0 &&  currTime - element <= 60000){
//                requestInWindow++;
//            }
//        }
//        return Math.max(0,Integer.parseInt(rateLimitRPM) - requestInWindow);
//    }
//
//    private long getRetryAfterSeconds(String clientIp) {
//        if ("moving".equalsIgnoreCase(rateLimitAlgo)) {
//            return getRetryAfterSliding(clientIp);
//        } else if ("fixed".equalsIgnoreCase(rateLimitAlgo)) {
//            return getRetryAfterFixed(clientIp);
//        } else {
//            throw new IllegalArgumentException("Unknown rate limiting algorithm: " + rateLimitAlgo);
//        }
//    }
//
//    private long getRetryAfterFixed(String clientIp) {
//        FixedInterval fixedInterval = (FixedInterval) requestMap.get(clientIp);
//        if (fixedInterval == null) return 0;
//        long currTime = System.currentTimeMillis();
//        long minMills = 60000;
//
//        return Math.max(0,(fixedInterval.startTime + minMills - currTime)/1000);
//
//    }
//
//    private long getRetryAfterSliding(String clientIp) {
//        CircularBuffer circularBuffer = (CircularBuffer) requestMap.get(clientIp);
//        if (circularBuffer == null) return 0;
//        long currTime = System.currentTimeMillis();
//        long minMills = 60000;
//        return Math.max(0,(circularBuffer.getLatest() + minMills - currTime)/1000);
//    }
//
//    private static class FixedInterval{
//        long startTime;
//        int requestCount;
//
//        public FixedInterval(long startTime, int requestCount){
//            this.startTime = startTime;
//            this.requestCount = requestCount;
//        }
//    }
//
//    private static class CircularBuffer {
//        private final long[] buffer;
//        private int head = 0;// Points to the oldest element
//        private int tail = 0;// Points to the next empty slot
//        private int size = 0;// Current size of the buffer
//        private final int capacity; // Maximum capacity of the buffer
//
//        public CircularBuffer(int capacity) {
//            this.capacity = capacity;
//            buffer = new long[capacity];
//        }
//        public boolean add(long element) {
//            long minMills = 60000;
//            if (size < capacity) {
//                buffer[tail] = element;
//                tail = (tail + 1) % capacity;
//                size++;
//                return true;
//            } else {
//                long oldest = buffer[head];
//                if (element - oldest < minMills) {
//                    return false;
//                } else {
//                    buffer[head] = element;
//                    head = (head + 1) % capacity;
//                    tail = (tail + 1) % capacity;
//                    return true;
//                }
//            }
//        }
//        public long getLatest(){
//            return buffer[head];
//        }
//
//        @Override
//        public String toString(){
//            return  Arrays.toString(buffer);
//        }
//    }
//
//
//}