package com.beauce.kata;

public class AprilFoolsApp {
    public static void main(String[] args) {
        PrankGenerator generator = new PrankGenerator();

        System.out.println(generator.generatePrank("Alice", "employee"));
        System.out.println(generator.generatePrank("Bob", "manager"));
        System.out.println(generator.generatePrank("Charlie", "developer"));
    }
}
