package com.methods_common_to_all_objects;

/**
 * Demonstrates the benefits of constant-specific methods in enums
 * by comparing different approaches to implementing operation-specific behavior.
 * 
 * Key benefits of constant-specific methods:
 * 1. Type Safety: Compiler ensures all methods are implemented for each constant
 * 2. Encapsulation: Each constant encapsulates its own behavior and data
 * 3. Maintainability: Adding new operations doesn't require modifying existing code
 * 4. Performance: No runtime type checking or object creation overhead
 * 5. Error Handling: Each constant can handle errors in its own way
 */
public class ConstantSpecificMethods {
    /**
     * Approach 1: Using constant-specific methods (Recommended)
     * 
     * Advantages:
     * - Each constant implements its own behavior
     * - Compiler ensures all methods are implemented
     * - No runtime type checking needed
     * - Easy to add new operations
     * - Each operation can have its own error handling
     * - No need for switch statements or default cases
     * - Better performance (no object creation or interface dispatch)
     * - Self-documenting code
     */
    public enum Operation {
        PLUS {
            @Override
            public double apply(double x, double y) {
                return x + y;
            }
            
            @Override
            public String getSymbol() {
                return "+";
            }
        },
        MINUS {
            @Override
            public double apply(double x, double y) {
                return x - y;
            }
            
            @Override
            public String getSymbol() {
                return "-";
            }
        },
        TIMES {
            @Override
            public double apply(double x, double y) {
                return x * y;
            }
            
            @Override
            public String getSymbol() {
                return "×";
            }
        },
        DIVIDE {
            @Override
            public double apply(double x, double y) {
                if (y == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return x / y;
            }
            
            @Override
            public String getSymbol() {
                return "÷";
            }
        };

        // Abstract method that each constant must implement
        public abstract double apply(double x, double y);
        public abstract String getSymbol();
    }

    /**
     * Approach 2: Using switch statements (Not recommended)
     * 
     * Disadvantages:
     * - Need to modify switch statements when adding new operations
     * - Risk of forgetting to update all switch statements
     * - Need for default cases
     * - No compile-time checking for completeness
     * - Behavior is scattered across multiple methods
     * - No way to add operation-specific fields
     * - Less maintainable and more error-prone
     */
    public enum OperationSwitch {
        PLUS, MINUS, TIMES, DIVIDE;

        public double apply(double x, double y) {
            switch (this) {
                case PLUS: return x + y;
                case MINUS: return x - y;
                case TIMES: return x * y;
                case DIVIDE:
                    if (y == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    return x / y;
                default:
                    throw new AssertionError("Unknown operation: " + this);
            }
        }

        public String getSymbol() {
            switch (this) {
                case PLUS: return "+";
                case MINUS: return "-";
                case TIMES: return "×";
                case DIVIDE: return "÷";
                default:
                    throw new AssertionError("Unknown operation: " + this);
            }
        }
    }

    /**
     * Approach 3: Using strategy pattern with separate classes (Alternative)
     * 
     * Disadvantages:
     * - Need for separate classes
     * - Need for factory methods
     * - Object creation overhead
     * - Interface dispatch overhead
     * - More complex to maintain
     * - Need for runtime type checking
     * - Risk of null strategies
     */
    public interface OperationStrategy {
        double apply(double x, double y);
        String getSymbol();
    }

    public static class PlusOperation implements OperationStrategy {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }

        @Override
        public String getSymbol() {
            return "+";
        }
    }

    public static class MinusOperation implements OperationStrategy {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }

        @Override
        public String getSymbol() {
            return "-";
        }
    }

    public static void main(String[] args) {
        // Demonstrate constant-specific methods
        System.out.println("Using constant-specific methods:");
        double x = 10.0;
        double y = 5.0;
        
        for (Operation op : Operation.values()) {
            try {
                System.out.printf("%.1f %s %.1f = %.1f%n",
                    x, op.getSymbol(), y, op.apply(x, y));
            } catch (ArithmeticException e) {
                System.out.printf("%.1f %s %.1f = Error: %s%n",
                    x, op.getSymbol(), y, e.getMessage());
            }
        }

        // Compare with switch-based approach
        System.out.println("\nUsing switch statements:");
        for (OperationSwitch op : OperationSwitch.values()) {
            try {
                System.out.printf("%.1f %s %.1f = %.1f%n",
                    x, op.getSymbol(), y, op.apply(x, y));
            } catch (ArithmeticException e) {
                System.out.printf("%.1f %s %.1f = Error: %s%n",
                    x, op.getSymbol(), y, e.getMessage());
            }
        }

        // Compare with strategy pattern
        System.out.println("\nUsing strategy pattern:");
        OperationStrategy plus = new PlusOperation();
        OperationStrategy minus = new MinusOperation();
        System.out.printf("%.1f %s %.1f = %.1f%n",
            x, plus.getSymbol(), y, plus.apply(x, y));
        System.out.printf("%.1f %s %.1f = %.1f%n",
            x, minus.getSymbol(), y, minus.apply(x, y));
    }
} 