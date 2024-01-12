package com.fizzbuzz.restserver.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class FizzBuzzRequest {
    public int int1;
    public int int2;
    public int limit;
    public String str1;
    public String str2;

    public FizzBuzzRequest(int int1, int int2, int limit, String str1, String str2) {
        this.int1 = int1;
        this.int2 = int2;
        this.limit = limit;
        this.str1 = str1;
        this.str2 = str2;
    }

    // Equals and hashCode methods are required for using FizzBuzzRequest as a key in the statistics map
    // Generated using your IDE or manually implemented

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FizzBuzzRequest that = (FizzBuzzRequest) o;
        return int1 == that.int1 &&
                int2 == that.int2 &&
                limit == that.limit &&
                Objects.equals(str1, that.str1) &&
                Objects.equals(str2, that.str2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(int1, int2, limit, str1, str2);
    }
}
