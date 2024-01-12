package com.fizzbuzz.restserver.demo.service;

import com.fizzbuzz.restserver.demo.dto.FizzBuzzRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

class FizzBuzzServiceTest {


    @Test
    void testGenerateFizzBuzz() {
        FizzBuzzService fizzBuzzService = new FizzBuzzService();
        FizzBuzzRequest request = new FizzBuzzRequest(3, 5, 15, "fizz", "buzz");
        List<String> result = fizzBuzzService.generateFizzBuzz(request);

        assertEquals("1", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("fizz", result.get(2));
        assertEquals("4", result.get(3));
        assertEquals("buzz", result.get(4));
        // ... add more assertions based on the Fizz-Buzz logic
    }

    @Test
    void testGetStatistics() {
        FizzBuzzService fizzBuzzService = new FizzBuzzService();
        FizzBuzzRequest request1 = new FizzBuzzRequest(3, 5, 15, "fizz", "buzz");
        FizzBuzzRequest request2 = new FizzBuzzRequest(2, 4, 10, "fizz", "buzz");
        // Assume some requests have been made
        fizzBuzzService.generateFizzBuzz(request1);
        fizzBuzzService.generateFizzBuzz(request2);

        // Get and validate statistics
        assertTrue(fizzBuzzService.getStatistics().containsKey("mostUsedRequest"));
        assertEquals(1, fizzBuzzService.getStatistics().get("hits"));
    }
}

