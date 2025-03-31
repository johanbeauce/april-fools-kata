# April Fools' Extensible Prank Generator

## Overview
This kata is designed to introduce the **Open/Closed Principle** (OCP) of SOLID by refactoring a rigid prank generator into an **extensible** system using the **Strategy Pattern**.

The theme? **April Fools' Day!** Your task is to refactor a prank generator so that **new pranks can be added without modifying the existing logic**.

## Goal
The initial implementation is **not** OCP-compliant:
- Adding a new prank requires modifying the existing `if-else` structure.
- The system is not scalable and hard to maintain.

Your mission is to **refactor the prank generator** so that:  
✅ New pranks can be **added easily** without modifying existing code.  
✅ Each prank follows a **Strategy Pattern** for better modularity.  
✅ The system adheres to the **Open/Closed Principle**.

## Starting Code (Before Refactoring)
The current implementation is a **monolithic if-else structure**:

```java
class PrankGenerator {
    public String generatePrank(String name, String role) {
        if (role.equals("employee")) {
            return "Congratulations " + name + "! You've been promoted to Chief Joke Officer!";
        } else if (role.equals("developer")) {
            return "[CRITICAL ERROR] A fatal bug has been detected in your IDE! Error code: APR-001.";
        } else if (role.equals("manager")) {
            return "URGENT: Emergency meeting with the CEO in 5 minutes. Prepare a presentation!";
        } else {
            return "April Fools, " + name + "!";
        }
    }
}
```
```java
public class AprilFoolsApp {
    public static void main(String[] args) {
        PrankGenerator generator = new PrankGenerator();
        System.out.println(generator.generatePrank("Alice", "employee"));
        System.out.println(generator.generatePrank("Bob", "manager"));
        System.out.println(generator.generatePrank("Charlie", "developer"));
    }
}
```
## Bonus step for fast finishers:

### A) Add a new prank.

Add a new prank for a "HR" role to verify your new prank generator works.

---
### B) Allow Multiple Applicable Strategies: 

a target can now match multiple strategies (e.g. a DEVELOPER who is also a MANAGER). Add a prank for this case. Verify your prank generator can handle if the target is developer and manager.

💡 **Challenge**: Do you stick with List<PrankStrategy> or switch to a Chain of Responsibility?

---
### C) Time-Based Pranks:

The prank should be different by time of day. For example, a morning prank could be different from an afternoon prank. Implement this feature and ensure the prank changes based on the time of day.

💡 **Challenge**: How far can you stretch the Strategy Pattern before you need another pattern (e.g. Rules Engine)?

---
### D) Strategy Composition:

Combine multiple strategies into composite strategies.
* For instance, a strategy that wraps another to add emojis, uppercase text, or a disclaimer.
* Implement a ComposablePrankStrategy decorator.

💡 **Challenge**: Design a system where behaviors can be added like LEGO bricks.
