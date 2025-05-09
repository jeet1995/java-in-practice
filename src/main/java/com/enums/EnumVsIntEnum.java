package com.methods_common_to_all_objects;

/**
 * Demonstrates the benefits of Java enums over the traditional int enum pattern.
 * This example shows type safety, compile-time checking, and additional features
 * that enums provide over int constants.
 */
public class EnumVsIntEnum {
    // Traditional int enum pattern
    public static final int SEASON_SPRING = 0;
    public static final int SEASON_SUMMER = 1;
    public static final int SEASON_FALL = 2;
    public static final int SEASON_WINTER = 3;

    // Java enum
    public enum Season {
        SPRING("Spring", "March-May"),
        SUMMER("Summer", "June-August"),
        FALL("Fall", "September-November"),
        WINTER("Winter", "December-February");

        private final String displayName;
        private final String months;

        Season(String displayName, String months) {
            this.displayName = displayName;
            this.months = months;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getMonths() {
            return months;
        }

        public boolean isWarm() {
            return this == SUMMER || this == SPRING;
        }
    }

    public static void main(String[] args) {
        // Demonstrate type safety
        System.out.println("Type Safety Examples:");
        int season = SEASON_SPRING;
        // No type checking - can assign any int
        season = 42;  // Compiles fine, but logically incorrect
        System.out.println("Int enum allows invalid values: " + season);

        Season enumSeason = Season.SPRING;
        // enumSeason = 42;  // Won't compile - type safety!
        // enumSeason = "SUMMER";  // Won't compile - type safety!
        System.out.println("Java enum prevents invalid values");

        // Demonstrate additional enum features
        System.out.println("\nEnum Features:");
        System.out.println("Name: " + Season.SUMMER.name());  // Built-in name() method
        System.out.println("Ordinal: " + Season.SUMMER.ordinal());  // Built-in ordinal() method
        System.out.println("Display Name: " + Season.SUMMER.getDisplayName());  // Custom method
        System.out.println("Months: " + Season.SUMMER.getMonths());  // Custom method
        System.out.println("Is Warm: " + Season.SUMMER.isWarm());  // Custom method

        // Demonstrate iteration
        System.out.println("\nIteration:");
        System.out.println("All seasons:");
        for (Season s : Season.values()) {
            System.out.println(s.getDisplayName() + " (" + s.getMonths() + ")");
        }

        // Demonstrate switch statement benefits
        System.out.println("\nSwitch Statement Benefits:");
        processSeason(Season.SUMMER);
        // processSeason(42);  // Won't compile - type safety!

        // Demonstrate string conversion
        System.out.println("\nString Conversion:");
        String seasonStr = "SUMMER";
        Season fromString = Season.valueOf(seasonStr);  // Safe conversion
        System.out.println("Converted from string: " + fromString);
        
        // Demonstrate comparison
        System.out.println("\nComparison:");
        System.out.println("SUMMER > SPRING: " + (Season.SUMMER.ordinal() > Season.SPRING.ordinal()));
    }

    private static void processSeason(Season season) {
        switch (season) {
            case SPRING:
                System.out.println("Spring is here!");
                break;
            case SUMMER:
                System.out.println("Summer is hot!");
                break;
            case FALL:
                System.out.println("Fall is colorful!");
                break;
            case WINTER:
                System.out.println("Winter is cold!");
                break;
            // No default needed - compiler ensures all cases are handled
        }
    }
} 