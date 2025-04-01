package com.creation_and_destroying_objects;

import java.io.Serializable;

public class TrueSingleton implements Serializable {
    private static TrueSingleton instance;
    
    private TrueSingleton() {
        if (instance != null) {
            throw new IllegalStateException("Singleton instance already exists. Use getInstance() method.");
        }
    }

    /**
     * Returns the singleton instance using lazy initialization with synchronization.
     * 
     * Thread Safety:
     * - The synchronized keyword ensures only one thread can execute this method at a time
     * - This prevents multiple instance creation in multi-threaded environments
     * 
     * Trade-offs:
     * - Thread-safe but potentially impacts performance
     * - Every call to getInstance() acquires a lock, even after initialization
     * - Consider using double-checked locking or holder pattern for better performance
     * 
     * @return the singleton instance
     */
    public static synchronized TrueSingleton getInstance() {
        if (instance == null) {
            instance = new TrueSingleton();
        }
        return instance;
    }

    /**
     * Prevents creation of new instance during deserialization.
     * This method is called by the JVM during deserialization.
     * 
     * @return the singleton instance
     */
    private Object readResolve() {
        return getInstance();
    }
} 