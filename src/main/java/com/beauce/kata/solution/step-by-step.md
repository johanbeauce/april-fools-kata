# Refactoring to Strategy Pattern with Open/Closed Principle

This guide walks you through refactoring a prank generator using the **Strategy Pattern** and applying the **Open/Closed Principle (OCP)**. You'll start with basic logic and evolve it toward a clean, extensible design.

---

## Step 1: Create a Unit Test with 100% Coverage

Start with a simple `PrankGenerator` and write a test that covers all the logic:

```java
@Test
void should_generate_prank_based_on_role() {
    private PrankGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new PrankGenerator();
    }

    @Test
    void generate_prank_for_employee() {
        var result = generator.generatePrank(new Target("Alice", "employee"));
        assertThat(result).isEqualTo("Congratulations Alice! You have been promoted to Chief Joke Officer!");
    }

    @Test
    void generate_prank_for_developer() {
        var result = generator.generatePrank(new Target("Bob", "developer"));
        assertThat(result).isEqualTo("[CRITICAL ALERT] A fatal error has been detected in your IDE! Error code: APR-001.");
    }

    @Test
    void generate_prank_for_manager() {
        var result = generator.generatePrank(new Target("Charlie", "manager"));
        assertThat(result).isEqualTo("URGENT: Surprise meeting with the CEO in 5 minutes. Prepare a presentation!");
    }

    @Test
    void generate_prank_for_other() {
        var result = generator.generatePrank(new Target("Dave", "other"));
        assertThat(result).isEqualTo("April Fools, Dave!");
    }
}
```

---

## Step 2: Introduce `Target` Record

Use IntelliJ's **Refactor > Introduce Parameter Object** to group `name` and `role` into a `Target`:

```java
public record Target(String name, String role) {
}
```

---

## Step 3: Create a `Role` Enum

Replace the role `String` with a type-safe enum:

```java
public enum Role {
    EMPLOYEE,
    DEVELOPER,
    MANAGER,
    OTHER
}
```

Update your `Target` accordingly:

```java
public record Target(String name, Role role) {
}
```

---

## Step 4: Create the `PrankStrategy` Interface

Introduce the interface for all prank strategies:

```java
public interface PrankStrategy {
    boolean isApplicable(Target target);
    String generatePrank(Target target);
}
```

---

## Step 5: Implement Each Strategy

### Employee
```java
public class EmployeePrankStrategy implements PrankStrategy {
    @Override
    public boolean isApplicable(Target target) {
        return target.role() == Role.EMPLOYEE;
    }

    @Override
    public String generatePrank(Target target) {
        return "Congratulations %s! You have been promoted to Chief Joke Officer!"
                .formatted(target.name());
    }
}
```

### Developer
```java
public class DeveloperPrankStrategy implements PrankStrategy {
    @Override
    public boolean isApplicable(Target target) {
        return target.role() == Role.DEVELOPER;
    }

    @Override
    public String generatePrank(Target target) {
        return "[CRITICAL ALERT] A fatal error has been detected in your IDE! Error code: APR-001.";
    }
}
```

### Manager
```java
public class ManagerPrankStrategy implements PrankStrategy {
    @Override
    public boolean isApplicable(Target target) {
        return target.role() == Role.MANAGER;
    }

    @Override
    public String generatePrank(Target target) {
        return "URGENT: Surprise meeting with the CEO in 5 minutes. Prepare a presentation!";
    }
}
```

### Default (Other)
```java
public class DefaultPrankStrategy implements PrankStrategy {
    @Override
    public boolean isApplicable(Target target) {
        return true;
    }

    @Override
    public String generatePrank(Target target) {
        return "April Fools, %s!".formatted(target.name());
    }
}
```

---

## Step 6: Modify `PrankGenerator` to Use Strategies

```java
public class PrankGenerator {

    private final List<PrankStrategy> strategies;

    public PrankGenerator(List<PrankStrategy> strategies) {
        this.strategies = strategies;
    }

    public String generatePrank(Target target) {
        return strategies.stream()
                .filter(strategy -> strategy.isApplicable(target))
                .findFirst()
                .orElseGet(DefaultPrankStrategy::new)
                .generatePrank(target);
    }
}
```

---

## Step 7: Refactor with Role-Based Strategy Instantiation

As target role is the only element to choose the strategy, make `Role` enum responsible for returning the appropriate strategy:

```java
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
```

Remove `isApplicable()` from the interface — it's no longer needed:

```java
public interface PrankStrategy {
    String generatePrank(Target target);
}
```

---

## Final Version of `PrankGenerator`

Simple and clean:

```java
public class PrankGenerator {
    public String generatePrank(Target target) {
        return target
                .role()
                .getPrankStrategy()
                .generatePrank(target);
    }
}
```

---

## Benefits

- ✅ Easy to extend with new `Role` and `Strategy` without touching core logic.
- ✅ Aligned with **Open/Closed Principle**.
- ✅ Clear separation of concerns and responsibility.