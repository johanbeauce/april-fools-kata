package com.beauce.kata.solution;

public class DefaultPrankStrategy implements PrankStrategy {
    @Override
    public String generatePrank(Target target) {
        return "April Fools, %s!".formatted(target.name());
    }
}
