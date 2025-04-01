package com.creation_and_destroying_objects;

import java.util.WeakHashMap;
import java.util.Map;

/**
 * Demonstrates how WeakHashMap works with garbage collection.
 * WeakHashMap allows its keys to be garbage collected when they are no longer referenced elsewhere.
 * This is particularly useful for caching scenarios where you want the cache to automatically
 * clear entries when the keys are no longer needed.
 */
public class WeakHashMapExample {
    public static void main(String[] args) {
        // Create a WeakHashMap
        WeakHashMap<Key, String> map = new WeakHashMap<>();
        
        // Create a key and store it in a variable
        Key key1 = new Key("Key1");
        map.put(key1, "Value1");
        
        // Create another key but don't store the reference
        map.put(new Key("Key2"), "Value2");
        
        // Print initial state
        System.out.println("Initial map size: " + map.size());
        System.out.println("Initial map contents:");
        printMapContents(map);
        
        // Clear the reference to key1
        key1 = null;
        
        // Suggest garbage collection
        System.gc();
        
        // Wait a bit to allow GC to run
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Print final state
        System.out.println("\nAfter garbage collection:");
        System.out.println("Final map size: " + map.size());
        System.out.println("Final map contents:");
        printMapContents(map);
        
        // Demonstrate String interning and WeakHashMap behavior
        System.out.println("\n=== String Interning Demonstration ===");
        
        // String interning is a way to reuse String objects in memory
        // When you create a String literal (using quotes), Java automatically interns it
        // This means all identical String literals share the same object in memory
        
        // This string is interned (stored in the String pool)
        String internedString = "Hello";
        // This string is also interned and references the same object
        String anotherInternedString = "Hello";
        // This creates a new String object, not interned
        String nonInternedString = new String("Hello");
        
        // Demonstrate that interned strings are the same object
        System.out.println("Interned strings are the same object: " + 
            (internedString == anotherInternedString)); // true
        System.out.println("Non-interned string is a different object: " + 
            (internedString == nonInternedString)); // false
        
        // Now demonstrate WeakHashMap behavior with interned vs non-interned strings
        WeakHashMap<String, String> stringMap = new WeakHashMap<>();
        
        // Add both interned and non-interned strings
        stringMap.put(internedString, "Value1");      // Interned string
        stringMap.put(nonInternedString, "Value2");   // Non-interned string
        
        System.out.println("\nString map initial size: " + stringMap.size());
        
        // Clear the non-interned string reference
        nonInternedString = null;
        System.gc();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("String map final size: " + stringMap.size());
        System.out.println("Explanation: The non-interned string entry was removed because its key " +
            "was garbage collected, but the interned string entry remains because interned strings " +
            "are never garbage collected.");
    }
    
    private static void printMapContents(WeakHashMap<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

/**
 * Custom key class for demonstration.
 * Using a custom class makes it easier to demonstrate WeakHashMap behavior
 * compared to String keys which are often interned.
 */
class Key {
    private final String name;
    
    public Key(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return this.name.equals(key.name);
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
} 