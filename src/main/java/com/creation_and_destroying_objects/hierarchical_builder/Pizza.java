package com.creation_and_destroying_objects.hierarchical_builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * Abstract base class for pizzas demonstrating the hierarchical builder pattern.
 * This pattern is useful when you have:
 * 1. A class hierarchy where each subclass needs its own builder
 * 2. Many constructor parameters, some optional
 * 3. Need for immutable objects
 * 4. Need to enforce invariants during construction
 */
public abstract class Pizza {
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    private final Set<Topping> toppings;  // Immutable after construction

    /**
     * Abstract builder class using recursive generics to support inheritance in builders.
     * @param <T> The concrete builder type (subclass of Builder)
     *
     * The 'extends Builder<T>' syntax creates a recursive type bound that ensures
     * type safety throughout the builder hierarchy. This is known as the "simulated self-type" idiom.
     */
    abstract static class Builder<T extends Builder<T>> {
        // Using EnumSet for efficiency when dealing with enum-based sets
        private EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        /**
         * Adds a topping to the pizza.
         * Returns the builder instance for method chaining.
         * @param topping the topping to add
         * @return this builder
         * @throws NullPointerException if topping is null
         */
        public T addTopping(Topping topping) {
            this.toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        /**
         * Creates the pizza instance.
         * Each concrete subclass must implement this to return its specific type.
         */
        abstract Pizza build();

        /**
         * Method that returns "this" with the correct type.
         * This is the key to making the fluent builder pattern work with inheritance.
         * Each concrete builder subclass must override this method to return "this".
         */
        protected abstract T self();
    }

    /**
     * Protected constructor that takes a builder.
     * Makes a defensive copy of toppings to ensure immutability.
     */
    Pizza(Builder<?> builder) {
        this.toppings = builder.toppings.clone();
    }

    public Set<Topping> getToppings() {
        return this.toppings;
    }
} 