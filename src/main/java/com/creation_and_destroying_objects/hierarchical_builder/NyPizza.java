package com.creation_and_destroying_objects.hierarchical_builder;

import java.util.Objects;

/**
 * Concrete Pizza subclass representing a New York style pizza.
 * Demonstrates how to extend the hierarchical builder pattern with required parameters.
 */
public class NyPizza extends Pizza {
    public enum Size { SMALL, MEDIUM, LARGE }
    private final Size size;  // Required parameter specific to NY Pizza

    /**
     * Concrete builder for NY Pizza.
     * Shows how to:
     * 1. Extend the base builder with specific type parameters
     * 2. Add required parameters through constructor
     * 3. Maintain type safety in the builder hierarchy
     */
    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;  // Required parameter must be set in constructor

        /**
         * Constructor enforces required parameters.
         * Uses Objects.requireNonNull to ensure non-null values.
         * @param size the size of the NY Pizza (required)
         */
        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        /**
         * Creates a new NyPizza instance.
         * Note the covariant return type - returns NyPizza instead of Pizza.
         */
        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        /**
         * Returns this builder instance.
         * Critical for method chaining in builder pattern with inheritance.
         */
        @Override
        protected Builder self() {
            return this;
        }
    }

    /**
     * Private constructor ensures Pizza can only be constructed through its Builder.
     * Calls super(builder) to handle common Pizza attributes.
     */
    private NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }

    public Size getSize() {
        return this.size;
    }
} 