package com.methods_common_to_all_objects;

import java.util.Objects;

/**
 * Demonstrates how to avoid Liskov Substitution Principle violation
 * by using composition instead of inheritance for value types.
 * This approach maintains the equals contract while allowing for
 * extension of functionality.
 */
public class CompositionPoint {
    private final int x;
    private final int y;

    public CompositionPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CompositionPoint))
            return false;
        CompositionPoint p = (CompositionPoint) o;
        return p.x == x && p.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("Point[x=%d, y=%d]", x, y);
    }
}

/**
 * Instead of extending Point, this class contains a Point.
 * This maintains the equals contract while adding color functionality.
 */
class ColorPoint {
    private final CompositionPoint point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        this.point = new CompositionPoint(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public CompositionPoint asPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint))
            return false;
        ColorPoint cp = (ColorPoint) o;
        return cp.point.equals(point) && cp.color == color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, color);
    }

    @Override
    public String toString() {
        return String.format("ColorPoint[x=%d, y=%d, color=%s]", 
            point.getX(), point.getY(), color);
    }

    public static void main(String[] args) {
        // Create points
        CompositionPoint p = new CompositionPoint(1, 2);
        ColorPoint cp1 = new ColorPoint(1, 2, Color.RED);
        ColorPoint cp2 = new ColorPoint(1, 2, Color.BLUE);

        // Demonstrate proper equality behavior
        System.out.println("Point comparisons:");
        System.out.println("p.equals(cp1.asPoint()) = " + p.equals(cp1.asPoint()));     // true
        System.out.println("cp1.asPoint().equals(p) = " + cp1.asPoint().equals(p));     // true
        
        // Demonstrate proper color point behavior
        System.out.println("\nColorPoint comparisons:");
        System.out.println("cp1.equals(cp2) = " + cp1.equals(cp2));                     // false (different colors)
        
        ColorPoint cp3 = new ColorPoint(1, 2, Color.RED);
        System.out.println("cp1.equals(cp3) = " + cp1.equals(cp3));                     // true (same coordinates and color)
        
        // Demonstrate type safety
        System.out.println("\nType safety:");
        System.out.println("cp1.equals(p) = " + cp1.equals(p));                         // false (different types)
        System.out.println("p.equals(cp1) = " + p.equals(cp1));                         // false (different types)
        
        // Demonstrate proper hashCode behavior
        System.out.println("\nHashCode consistency:");
        System.out.println("cp1.hashCode() == cp3.hashCode() = " + 
            (cp1.hashCode() == cp3.hashCode()));                                         // true
        System.out.println("cp1.hashCode() == cp2.hashCode() = " + 
            (cp1.hashCode() == cp2.hashCode()));                                         // false
    }
} 