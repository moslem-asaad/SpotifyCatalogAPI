package com.example.catalog;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HttpLoadTestAsync {

    public String shortestPalindrome(String s) {
        if(isPalindrom(s)) return s;
        char[] sch = s.toCharArray();
        String res = s;
        for(int i = 0;i<sch.length;i++){
            res = sch[i] + res;
            if(isPalindrom(res)) break;
        }
        System.out.println(res.length());
        System.out.println(s.length());
        if(res.length() == s.length()/2){
            res = res.substring(0,(res.length() - (s.length() + countSimilarCharPrefix(s))));
            res = res + s;
        }
        return res;
    }

    private boolean isPalindrom(String s){
        String temp = reverse(s);
        return temp.equals(s);
    }

    private String reverse(String input) {
        char[] a = input.toCharArray();
        int l, r = a.length - 1;
        for (l = 0; l < r; l++, r--)
        {
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }

    private int countSimilarCharPrefix(String s){
        int i = 1;
        char[] sch = s.toCharArray();
        char c = sch[0];
        while(i<sch.length && sch[i] == c) i++;
        return i;
    }


//    private static final String TARGET_URL = "http://localhost:8080/";
//    private static final int REQUEST_COUNT = 1000;

    public static void main(String[] args) {
        HttpLoadTestAsync ht = new HttpLoadTestAsync();
        System.out.println(ht.shortestPalindrome("abcd"));
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(TARGET_URL))
//                .GET()
//                .build();
//
//        List<CompletableFuture<Void>> futures = new ArrayList<>();
//        Instant start = Instant.now();
//
//        for (int i = 0; i < REQUEST_COUNT; i++) {
//            int finalI = i;
//            CompletableFuture<Void> future = client.sendAsync(request, HttpResponse.BodyHandlers.discarding())
//                    .thenApply(response -> Duration.between(start, Instant.now()).toMillis())
//                    .thenAccept(duration -> System.out.println("Request " + (finalI + 1) + " duration: " + duration + " ms"));
//            futures.add(future);
//        }
//
//        CompletableFuture.allOf(futures.toArray(new CompletableFuture[REQUEST_COUNT])).join();
//
//        System.out.println("All requests completed.");
    }
}

