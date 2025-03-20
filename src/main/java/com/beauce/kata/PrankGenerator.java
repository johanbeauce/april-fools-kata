package com.beauce.kata;

public class PrankGenerator {
    public String generatePrank(String name, String role) {
        if (role.equals("employee")) {
            return "Congratulations " + name + "! You have been promoted to Chief Joke Officer!";
        } else if (role.equals("developer")) {
            return "[CRITICAL ALERT] A fatal error has been detected in your IDE! Error code: APR-001.";
        } else if (role.equals("manager")) {
            return "URGENT: Surprise meeting with the CEO in 5 minutes. Prepare a presentation!";
        } else {
            return "April Fools, " + name + "!";
        }
    }
}
