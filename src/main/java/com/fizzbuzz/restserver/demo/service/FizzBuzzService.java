package com.fizzbuzz.restserver.demo.service;

// FizzBuzzService.java
import com.fizzbuzz.restserver.demo.dto.FizzBuzzRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class FizzBuzzService {
    private static final Logger logger = LoggerFactory.getLogger(FizzBuzzService.class);


    private final Map<FizzBuzzRequest, Integer> statistics = new HashMap<>();

    public List<String> generateFizzBuzz(FizzBuzzRequest request) {
        try {
            List<String> result = IntStream.rangeClosed(1, request.getLimit())
                    .mapToObj(i -> generateFizzBuzzValue(i, request.getInt1(), request.getInt2(), request.getStr1(), request.getStr2()))
                    .collect(Collectors.toList());

            statistics.put(request, statistics.getOrDefault(request, 0) + 1);
            return result;
        }catch (Exception e){
            logger.info("exception occured with this error " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private String generateFizzBuzzValue(int num, int int1, int int2, String str1, String str2) {
        if (num % int1 == 0) {
            return (num % int2 == 0) ? str1 + str2 : str1;
        } else if (num % int2 == 0) {
            return str2;
        } else {
            return String.valueOf(num);
        }
    }

    public Map<String, Object> getStatistics() {
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
        return result;
    }
}

