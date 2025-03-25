package com.beauce.kata.solution;

import java.util.function.Supplier;

public enum Role {
    EMPLOYEE(EmployeePrankStrategy::new),
    DEVELOPER(DeveloperPrankStrategy::new),
    MANAGER(ManagerPrankStrategy::new),
    OTHER(DefaultPrankStrategy::new);

    private final Supplier<PrankStrategy> prankStrategy;

    Role(Supplier<PrankStrategy> prankStrategy) {
        this.prankStrategy = prankStrategy;
    }

    public PrankStrategy getPrankStrategy() {
        return prankStrategy.get();
    }
}
