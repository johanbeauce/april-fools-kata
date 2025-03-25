package com.beauce.kata.solution;

public class ManagerPrankStrategy implements PrankStrategy {
    @Override
    public String generatePrank(Target target) {
        return "URGENT: Surprise meeting with the CEO in 5 minutes. Prepare a presentation!";
    }
}
