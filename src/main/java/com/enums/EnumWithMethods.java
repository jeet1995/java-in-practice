package com.methods_common_to_all_objects;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates practical use cases for enum methods through a real-world example
 * of a restaurant's operating hours and menu management.
 */
public class EnumWithMethods {
    public enum Day {
        MONDAY("Monday", false),
        TUESDAY("Tuesday", false),
        WEDNESDAY("Wednesday", false),
        THURSDAY("Thursday", false),
        FRIDAY("Friday", false),
        SATURDAY("Saturday", true),
        SUNDAY("Sunday", true);

        private final String displayName;
        private final boolean isWeekend;

        Day(String displayName, boolean isWeekend) {
            this.displayName = displayName;
            this.isWeekend = isWeekend;
        }

        // Business logic methods
        public boolean isWeekend() {
            return isWeekend;
        }

        public String getDisplayName() {
            return displayName;
        }

        public boolean isOpen() {
            return this != SUNDAY;  // Restaurant is closed on Sundays
        }

        public List<MenuItem> getAvailableMenu() {
            return isWeekend ? MenuItem.getWeekendMenu() : MenuItem.getWeekdayMenu();
        }

        public static Day fromDate(LocalDate date) {
            return values()[date.getDayOfWeek().getValue() - 1];
        }
    }

    public enum MenuItem {
        // Weekday items
        BURGER(10.99, false),
        SALAD(8.99, false),
        SOUP(6.99, false),
        
        // Weekend items
        STEAK(24.99, true),
        LOBSTER(34.99, true),
        WINE_PAIRING(15.99, true);

        private final double price;
        private final boolean weekendOnly;

        MenuItem(double price, boolean weekendOnly) {
            this.price = price;
            this.weekendOnly = weekendOnly;
        }

        // Business logic methods
        public double getPrice() {
            return price;
        }

        public boolean isWeekendOnly() {
            return weekendOnly;
        }

        public static List<MenuItem> getWeekdayMenu() {
            return Arrays.stream(values())
                    .filter(item -> !item.weekendOnly)
                    .toList();
        }

        public static List<MenuItem> getWeekendMenu() {
            return Arrays.stream(values())
                    .filter(item -> item.weekendOnly)
                    .toList();
        }

        public String getFormattedPrice() {
            return String.format("$%.2f", price);
        }
    }

    public static void main(String[] args) {
        // Demonstrate business logic in enums
        System.out.println("Restaurant Operations Example:\n");

        // Check if restaurant is open on different days
        System.out.println("Restaurant Hours:");
        for (Day day : Day.values()) {
            System.out.printf("%s: %s%n", 
                day.getDisplayName(), 
                day.isOpen() ? "Open" : "Closed");
        }

        // Show different menus for weekdays and weekends
        System.out.println("\nMenu Availability:");
        Day today = Day.fromDate(LocalDate.now());
        System.out.println("Today's Menu (" + today.getDisplayName() + "):");
        today.getAvailableMenu().forEach(item -> 
            System.out.printf("- %s: %s%n", item.name(), item.getFormattedPrice()));

        // Demonstrate business rules
        System.out.println("\nBusiness Rules:");
        System.out.println("Weekend Items:");
        MenuItem.getWeekendMenu().forEach(item ->
            System.out.printf("- %s: %s%n", item.name(), item.getFormattedPrice()));

        // Demonstrate date-based operations
        LocalDate nextWeekend = LocalDate.now().plusDays(7);
        Day weekendDay = Day.fromDate(nextWeekend);
        System.out.println("\nNext Weekend Menu (" + weekendDay.getDisplayName() + "):");
        weekendDay.getAvailableMenu().forEach(item ->
            System.out.printf("- %s: %s%n", item.name(), item.getFormattedPrice()));
    }
} 