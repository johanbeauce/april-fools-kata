package com.beauce.kata.solution;

public class DeveloperPrankStrategy implements PrankStrategy {
    @Override
    public String generatePrank(Target target) {
        return "[CRITICAL ALERT] A fatal error has been detected in your IDE! Error code: APR-001.";
    }
}
