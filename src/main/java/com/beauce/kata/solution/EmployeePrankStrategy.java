package com.beauce.kata.solution;

public class EmployeePrankStrategy implements PrankStrategy {
    @Override
    public String generatePrank(Target target) {
        return "Congratulations %s! You have been promoted to Chief Joke Officer!"
                .formatted(target.name());
    }
}
