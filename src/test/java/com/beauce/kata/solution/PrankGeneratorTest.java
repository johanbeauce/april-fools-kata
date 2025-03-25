package com.beauce.kata.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrankGeneratorTest {

    private PrankGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new PrankGenerator();
    }

    @Test
    void generate_prank_for_employee() {
        var result = generator.generatePrank(new Target("Alice", Role.EMPLOYEE));
        assertThat(result).isEqualTo("Congratulations Alice! You have been promoted to Chief Joke Officer!");
    }

    @Test
    void generate_prank_for_developer() {
        var result = generator.generatePrank(new Target("Bob", Role.DEVELOPER));
        assertThat(result).isEqualTo("[CRITICAL ALERT] A fatal error has been detected in your IDE! Error code: APR-001.");
    }

    @Test
    void generate_prank_for_manager() {
        var result = generator.generatePrank(new Target("Charlie", Role.MANAGER));
        assertThat(result).isEqualTo("URGENT: Surprise meeting with the CEO in 5 minutes. Prepare a presentation!");
    }

    @Test
    void generate_prank_for_other() {
        var result = generator.generatePrank(new Target("Dave", Role.OTHER));
        assertThat(result).isEqualTo("April Fools, Dave!");
    }
}