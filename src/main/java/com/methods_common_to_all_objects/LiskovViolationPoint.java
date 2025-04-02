package com.methods_common_to_all_objects;

/**
 * Demonstrates how a seemingly reasonable equals implementation can violate
 * Liskov Substitution Principle (LSP) through inheritance.
 * 
 * This example shows how adding a color property in a subclass breaks
 * the symmetry requirement of equals contract.
 */
public class LiskovViolationPoint {
    private final int x;
    private final int y;

    public LiskovViolationPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LiskovViolationPoint))
            return false;
        LiskovViolationPoint p = (LiskovViolationPoint) o;
        return p.x == x && p.y == y;
    }

    // Violates hashCode-equals contract by not overriding hashCode
    // This is intentional to show another common mistake

    public static void main(String[] args) {
        // Create points
        LiskovViolationPoint p = new LiskovViolationPoint(1, 2);
        ColoredPoint cp = new ColoredPoint(1, 2, Color.RED);
        ColoredPoint cp2 = new ColoredPoint(1, 2, Color.BLUE);

        // Demonstrate symmetry violation
        System.out.println("p.equals(cp) = " + p.equals(cp));      // true
        System.out.println("cp.equals(p) = " + cp.equals(p));      // false
        
        // Demonstrate transitivity violation
        ColoredPoint cp3 = new ColoredPoint(1, 2, Color.RED);
        System.out.println("\nTransitivity violation:");
        System.out.println("p.equals(cp) = " + p.equals(cp));      // true
        System.out.println("p.equals(cp2) = " + p.equals(cp2));    // true
        System.out.println("cp.equals(cp2) = " + cp.equals(cp2));  // false
        
        // Demonstrate consistency violation with mixed comparisons
        System.out.println("\nMixed type comparisons:");
        System.out.println("cp.equals(cp3) = " + cp.equals(cp3));  // true (same color)
        System.out.println("cp.equals(cp2) = " + cp.equals(cp2));  // false (different color)
    }
}

class ColoredPoint extends LiskovViolationPoint {
    private final Color color;

    public ColoredPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColoredPoint))
            return false;
        return super.equals(o) && ((ColoredPoint) o).color == color;
    }
}

enum Color {
    RED, BLUE, GREEN
} 