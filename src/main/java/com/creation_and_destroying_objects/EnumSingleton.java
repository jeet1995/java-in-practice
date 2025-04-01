package com.creation_and_destroying_objects;

/**
 * Enum-based singleton implementation.
 * This is the preferred way to implement a singleton in Java because:
 * 1. Thread-safe by default (enum initialization is thread-safe)
 * 2. Handles serialization automatically (no need for readResolve)
 * 3. Prevents reflection attacks (enum constructors cannot be accessed via reflection)
 * 4. Guaranteed single instance by the JVM
 * 5. More concise than other implementations
 */
public enum EnumSingleton {
    /**
     * The single instance of the enum.
     * This is the only instance that will ever exist.
     */
    INSTANCE;

    /**
     * Private fields and methods can be added here.
     * They will be associated with the single INSTANCE.
     */
    private String data;

    /**
     * Private constructor (implicitly private in enums).
     * Cannot be called via reflection, making this implementation secure.
     */
    EnumSingleton() {
        // Initialize your singleton here
        this.data = "Initialized";
    }

    /**
     * Public methods to access and modify the singleton's state.
     */
    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * Example of a business method.
     */
    public void doSomething() {
        System.out.println("Doing something with data: " + this.data);
    }
} 