package com.beauce.kata.solution;

public class PrankGenerator {

    public String generatePrank(Target target) {
        return target
                .role()
                .getPrankStrategy()
                .generatePrank(target);
    }
}
