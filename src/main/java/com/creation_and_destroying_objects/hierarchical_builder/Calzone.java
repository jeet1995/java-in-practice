package com.creation_and_destroying_objects.hierarchical_builder;

/**
 * Concrete Pizza subclass representing a Calzone.
 * Demonstrates how to extend the hierarchical builder pattern with optional parameters.
 */
public class Calzone extends Pizza {
    private final boolean sauceInside;  // Optional parameter specific to Calzone

    /**
     * Concrete builder for Calzone.
     * Shows how to:
     * 1. Extend the base builder with optional parameters
     * 2. Use method chaining for optional parameters
     * 3. Set default values for optional parameters
     */
    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false; // Optional parameter with default value

        /**
         * Optional method to set sauce preference.
         * Demonstrates how to add optional parameters using the builder pattern.
         * @return this builder for method chaining
         */
        public Builder sauceInside() {
            this.sauceInside = true;
            return this;
        }

        /**
         * Creates a new Calzone instance.
         * Note the covariant return type - returns Calzone instead of Pizza.
         */
        @Override
        public Calzone build() {
            return new Calzone(this);
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
    private Calzone(Builder builder) {
        super(builder);
        this.sauceInside = builder.sauceInside;
    }

    public boolean isSauceInside() {
        return this.sauceInside;
    }
} 