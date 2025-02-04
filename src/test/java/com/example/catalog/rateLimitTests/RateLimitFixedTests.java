//package com.example.catalog.rateLimitTests;
//import com.example.catalog.interceptors.RateLimit;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.TestPropertySource;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(properties = {
//        "rate-limit.enabled=true",
//        "rate-limit.algo=fixed",
//        "rate-limit.rpm=10"
//})
//public class RateLimitFixedTests {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private static final String INTERNAL_ENDPOINT = "/internal";
//    private static final String XRateLimitRetryAfterSecondsHeader = "X-Rate-Limit-Retry-After-Seconds";
//    private static final String XRateLimitRemaining = "X-Rate-Limit-Remaining";
//
//    private String apiEndpoint;
//
//    @Autowired
//    private RateLimit rateLimit;
//    @LocalServerPort
//    private int port;
//
//    @BeforeEach
//    void setUp() {
//        rateLimit.clear();
//        apiEndpoint = "http://localhost:" + port + "/";
//    }
//
//    /**
//     * 1. make 10 requests
//     * 2. check status code is ok
//     * 3. check the remaining requests flag
//     * @throws InterruptedException
//     */
//    @Test
//    void testFixedRateLimitAllowRequestsWithinLimit() throws InterruptedException {
//        int rpm = Integer.parseInt(rateLimit.getRateLimitRPM());
//        // Perform 3 requests within the limit
//        for (int i = 0; i < rpm; i++) {
//            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
//            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the first 10 requests");
//            String remainingRequests = String.valueOf(rpm - (i + 1));
//            assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + i + 1 + " requests");
//        }
//    }
//
//    /**
//     * 1. make 10 requests
//     * 2. check status code is ok
//     * 3. check the remaining requests flag
//     * 4. moke 3 more requests
//     * 5. check status code is 429
//     * 6. check flags
//     * @throws InterruptedException
//     */
//    @Test
//    void testFixedRateLimitDisAllowRequestsMoreLimit() throws InterruptedException {
//        int rpm = Integer.parseInt(rateLimit.getRateLimitRPM());
//        for (int i = 0; i < rpm; i++) {
//            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
//            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the first 3 requests");
//            String remainingRequests = String.valueOf(rpm - (i + 1));
//            assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + i + 1 + " requests");
//        }
//        for (int i = 0; i < 3; i++) {
//            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
//            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(429)), "Expected status code to be 429 for the requests");
//            assertEquals("0", response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + "0" + " after " + i + 1 + " requests");
//            int retryAfter = Integer.parseInt(response.getHeaders().get(XRateLimitRetryAfterSecondsHeader).get(0));
//            assertTrue(retryAfter > 0);
//        }
//    }
//
//    /**
//     * 1. make 10 requests
//     * 2. check status code is ok
//     * 3. check the remaining requests flag
//     * 4. wait 60 seconds
//     * 5. make 3 more requests
//     * 6. check status code is 200
//     * 7. check flags
//     * @throws InterruptedException
//     */
//    @Test
//    void testFixedRateLimitWaitMinuteAllowRequests() throws InterruptedException {
//        int rpm = Integer.parseInt(rateLimit.getRateLimitRPM());
//        // Perform 3 requests within the limit
//        for (int i = 0; i < rpm; i++) {
//            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
//            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the first 3 requests");
//            String remainingRequests = String.valueOf(rpm - (i + 1));
//            assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + i + 1 + " requests");
//        }
//        Thread.sleep(60000);
//        for (int i = 0; i < rpm; i++) {
//            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
//            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the requests");
//            String remainingRequests = String.valueOf(rpm - (i + 1));
//            assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + i + 1 + " requests");
//        }
//    }
//    /**
//     * 1. make 5 requests with 20 seconds delay between each request
//     * 2. check status code
//     * 3. check flags
//     * 4. check that after 60 seconds the interval cleared
//     * @throws InterruptedException
//     */
//    @Test
//    void testFixed5RateLimitWaitDelays() throws InterruptedException {
//        int rpm = Integer.parseInt(rateLimit.getRateLimitRPM());
//        System.out.println("rrrrrrrrrrrbbbbbbbbmmmmmmm " + rpm);
//        ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
//        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the first  request");
//        String remainingRequests = String.valueOf(rpm -1);
//        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 1 + " requests");
//
//        Thread.sleep(20000);
//
//        response = restTemplate.getForEntity(apiEndpoint, String.class);
//        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the second request");
//        remainingRequests = String.valueOf(rpm -2);
//        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 2 + " requests");
//
//        Thread.sleep(20000);
//
//        response = restTemplate.getForEntity(apiEndpoint, String.class);
//        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the third request");
//        remainingRequests = String.valueOf(rpm -3);
//        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 3 + " requests");
//
//        Thread.sleep(20000);
//
//        response = restTemplate.getForEntity(apiEndpoint, String.class);
//        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the fourth request");
//        remainingRequests = String.valueOf(rpm - 1);
//        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 4 + " requests");
//
//        Thread.sleep(20000);
//
//        response = restTemplate.getForEntity(apiEndpoint, String.class);
//        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the fifth request");
//        remainingRequests = String.valueOf(rpm - 2);
//        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 5 + " requests");
//
//    }
//
//    @Test
//    public void testRateLimiterBypassesInternalEndpoint() {
//
//        for (int i = 0; i < 3; i++) {
//            ResponseEntity<String> response = restTemplate.getForEntity(INTERNAL_ENDPOINT, String.class);
//            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)));
//            assertFalse(response.getHeaders().containsKey(XRateLimitRemaining));
//        }
//    }
//
//
//}
