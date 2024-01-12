package com.fizzbuzz.restserver.demo.controller;

// FizzBuzzController.java
import com.fizzbuzz.restserver.demo.dto.FizzBuzzRequest;
import com.fizzbuzz.restserver.demo.service.FizzBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/restserver")
@RestController
public class FizzBuzzController {

    @Autowired
    FizzBuzzService fizzBuzzService;

    private final Map<FizzBuzzRequest, Integer> statistics = new HashMap<>();

    @GetMapping("/fizzbuzz")
    public ResponseEntity<List<String>> fizzBuzz(
            @RequestParam (value = "int1", required = true) int int1,
            @RequestParam (value = "int2", required = true) int int2,
            @RequestParam (value = "limit", required = true)int limit,
            @RequestParam (value = "str1", required = true) String str1,
            @RequestParam (value = "str2", required = true) String str2) {

        FizzBuzzRequest request = new FizzBuzzRequest(int1, int2, limit, str1, str2);

        List<String> result = fizzBuzzService.generateFizzBuzz(request);
        statistics.put(request, statistics.getOrDefault(request, 0) + 1);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> statistics() {
        Map.Entry<FizzBuzzRequest, Integer> mostUsedRequest = statistics.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        Map<String, Object> result = new HashMap<>();
        if (mostUsedRequest != null) {
            result.put("mostUsedRequest", mostUsedRequest.getKey());
            result.put("hits", mostUsedRequest.getValue());
        } else {
            result.put("message", "No statistics available");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
