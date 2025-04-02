package com.creation_and_destroying_objects;

import java.lang.ref.Cleaner;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the use of Cleaner with AutoCloseable as a safety net for resource cleanup.
 * This pattern is preferred over finalizers because:
 * 1. Cleaners are more reliable and predictable
 * 2. They don't block garbage collection
 * 3. They can be explicitly triggered
 * 4. They work well with try-with-resources
 */
public class CleanerWithAutocloseableExample {
    public static void main(String[] args) {
        // Create a resource that uses Cleaner
        try (ResourceWithCleaner resource = new ResourceWithCleaner("test-resource")) {
            System.out.println("Using resource: " + resource.getName());
            resource.doSomething();
            
            // Simulate some work
            TimeUnit.SECONDS.sleep(1);
            
            // Resource will be automatically cleaned up when try block ends
            System.out.println("Resource will be cleaned up now");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Force garbage collection to see cleanup in action
        System.gc();
        
        // Wait for cleanup to complete
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * A resource class that uses Cleaner for cleanup and implements AutoCloseable
 * for explicit resource management.
 */
class ResourceWithCleaner implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    
    private final String name;
    private final State state;
    private final Cleaner.Cleanable cleanable;
    
    // State class to hold the resources that need cleanup
    private static class State implements Runnable {
        private final String resourceName;
        
        State(String resourceName) {
            this.resourceName = resourceName;
            System.out.println("Resource " + resourceName + " initialized");
        }
        
        @Override
        public void run() {
            // This is the cleanup action that will be performed
            // either when close() is called or when the object is garbage collected
            System.out.println("Cleaning up resource: " + resourceName);
        }
    }
    
    public ResourceWithCleaner(String name) {
        this.name = name;
        this.state = new State(name);
        // Register this object with the cleaner
        this.cleanable = cleaner.register(this, state);
    }
    
    public String getName() {
        return name;
    }
    
    public void doSomething() {
        System.out.println("Resource " + name + " is doing something");
    }
    
    @Override
    public void close() {
        // Explicit cleanup through AutoCloseable
        System.out.println("Explicitly closing resource: " + name);
        cleanable.clean();
    }
    
    @Override
    protected void finalize() {
        // This is just for demonstration - in practice, you shouldn't override finalize
        // when using Cleaner
        System.out.println("Finalize called for: " + name);
    }
} 