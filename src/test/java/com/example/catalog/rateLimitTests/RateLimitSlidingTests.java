package com.example.catalog.rateLimitTests;

import com.example.catalog.interceptors.RateLimit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "rate-limit.enabled=true",
        "rate-limit.algo=moving",
        "rate-limit.rpm=3" // Allow 3 requests per minute
})
public class RateLimitSlidingTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String INTERNAL_ENDPOINT = "/internal";
    private static final String XRateLimitRetryAfterSecondsHeader = "X-Rate-Limit-Retry-After-Seconds";
    private static final String XRateLimitRemaining = "X-Rate-Limit-Remaining";

    private String apiEndpoint;

    @Autowired
    private RateLimit rateLimit;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        rateLimit.clear();
        apiEndpoint = "http://localhost:" + port + "/";
    }

    /**
     * 1. make 3 requests
     * 2. check status code is ok
     * 3. check the remaining requests flag
     * @throws InterruptedException
     */
    @Test
    void testSlidingRateLimitAllowRequestsWithinLimit() throws InterruptedException {
        int rpm = Integer.parseInt(rateLimit.getRateLimitRPM());
        // Perform 3 requests within the limit
        for (int i = 0; i < rpm; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the first 10 requests");
            String remainingRequests = String.valueOf(rpm - (i + 1));
            assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + i + 1 + " requests");
        }
    }

    /**
     * 1. make 3 requests
     * 2. check status code is ok
     * 3. check the remaining requests flag
     * 4. make 3 more requests
     * 5. check status code is 429
     * 6. check flags
     * @throws InterruptedException
     */
    @Test
    void testSlidingRateLimitDisAllowRequestsMoreLimit() throws InterruptedException {
        // Perform 3 requests within the limit
        for (int i = 0; i < 3; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the first 3 requests");
            String remainingRequests = String.valueOf(3 - (i + 1));
            assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + i + 1 + " requests");
        }
        for (int i = 0; i < 3; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(429)), "Expected status code to be 429 for the requests");
            assertEquals("0", response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + "0" + " after " + i + 1 + " requests");
            int retryAfter = Integer.parseInt(response.getHeaders().get(XRateLimitRetryAfterSecondsHeader).get(0));
            assertTrue(retryAfter > 0);
        }
    }

    /**
     * 1. make 3 requests
     * 2. check status code is ok
     * 3. check the remaining requests flag
     * 4. wait 60 seconds
     * 5. moke 3 more requests
     * 6. check status code is 429
     * 7. check flags
     * @throws InterruptedException
     */
    @Test
    void testSlidingRateLimitWaitMinuteAllowRequests() throws InterruptedException {
        // Perform 3 requests within the limit
        for (int i = 0; i < 3; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the first 3 requests");
            String remainingRequests = String.valueOf(3 - (i + 1));
            assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + i + 1 + " requests");
        }
        Thread.sleep(60000);
        for (int i = 0; i < 3; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the requests");
            String remainingRequests = String.valueOf(3 - (i + 1));
            assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + i + 1 + " requests");
        }
    }
    /**
     * 1. make 5 requests with 20 seconds delay between each request
     * 2. check status code
     * 3. check flags
     * 4. check for requests 60, 80 the XRateLimitRemaining is 0
     * @throws InterruptedException
     */
    @Test
    void testSliding5RateLimitWaitDelays() throws InterruptedException {

        ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the first  request");
        String remainingRequests = String.valueOf(2);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 1 + " requests");

        Thread.sleep(20000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the second request");
        remainingRequests = String.valueOf(1);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 2 + " requests");

        Thread.sleep(20000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the third request");
        remainingRequests = String.valueOf(0);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 3 + " requests");

        Thread.sleep(20000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the fourth request");
        remainingRequests = String.valueOf(0);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 4 + " requests");

        Thread.sleep(20000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the fifth request");
        remainingRequests = String.valueOf(0);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 5 + " requests");

    }

    /**
     * 1. make 6 requests {1,2,3,10,11,12}
     * 2. check status code for each request
     * 3. check flags
     * 4. send request at 13
     * 5. check flags
     * 6. make request at 65
     * 7. check flags, XRateLimitRemaining should be 2
     * 8. make request at 75
     * 9. check flags, XRateLimitRemaining should be 4
     * @throws InterruptedException
     */
    @Test
    void testSliding6RateLimitWaitDelays() throws InterruptedException {

        rateLimit.setRateLimitRPM("6");

        Thread.sleep(1000);

        ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the   request");
        String remainingRequests = String.valueOf(5);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 1 + " requests");

        Thread.sleep(1000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the   request");
        remainingRequests = String.valueOf(4);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 2 + " requests");

        Thread.sleep(1000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the   request");
        remainingRequests = String.valueOf(3);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 3 + " requests");

        Thread.sleep(7000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the   request");
        remainingRequests = String.valueOf(2);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 4 + " requests");

        Thread.sleep(1000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the   request");
        remainingRequests = String.valueOf(1);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 5 + " requests");

        Thread.sleep(1000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the   request");
        remainingRequests = String.valueOf(0);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 6 + " requests");

        Thread.sleep(1000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(429)), "Expected status code to be 429 for the   request");
        remainingRequests = String.valueOf(0);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 7 + " requests");
        int retryAfter = Integer.parseInt(response.getHeaders().get(XRateLimitRetryAfterSecondsHeader).get(0));
        assertTrue(retryAfter > 0);

        Thread.sleep(52000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the   request");
        remainingRequests = String.valueOf(2);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 8 + " requests");

        Thread.sleep(10000);

        response = restTemplate.getForEntity(apiEndpoint, String.class);
        assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the   request");
        remainingRequests = String.valueOf(4);
        assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + 9 + " requests");
        // 10007, 0, 72080, 65063, 64052 , 63037

    }

    @Test
    public void testRateLimiterBypassesInternalEndpoint() {

        for (int i = 0; i < 3; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(INTERNAL_ENDPOINT, String.class);
            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)));
            assertFalse(response.getHeaders().containsKey(XRateLimitRemaining));
        }
    }

    @Test
    void testSlidingRateLimitAllowRequestsWithinLimit10() throws InterruptedException {
        rateLimit.setRateLimitRPM("10");
        int rpm = Integer.parseInt(rateLimit.getRateLimitRPM());
        // Perform 3 requests within the limit
        for (int i = 0; i < rpm; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpoint, String.class);
            assertTrue(response.getStatusCode().equals(HttpStatusCode.valueOf(200)), "Expected status code to be 200 for the first 10 requests");
            String remainingRequests = String.valueOf(rpm - (i + 1));
            assertEquals(remainingRequests, response.getHeaders().get(XRateLimitRemaining).get(0), "Expected " + XRateLimitRemaining + " header to be " + remainingRequests + " after " + i + 1 + " requests");
        }
    }

}
