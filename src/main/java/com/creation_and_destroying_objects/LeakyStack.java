package com.creation_and_destroying_objects;

import java.util.Arrays;

/**
 * Demonstrates a memory leak in a Stack implementation.
 * The leak occurs because references in the backing array are not nullified
 * when elements are popped from the stack.
 */
public class LeakyStack {
    public static void main(String[] args) {
        // Create a stack
        Stack<String> stack = new Stack<>();
        
        // Push some large objects
        for (int i = 0; i < 1000; i++) {
            stack.push(createLargeObject(i));
        }
        
        System.out.println("Stack size after pushing: " + stack.size());
        System.out.println("Backing array length: " + stack.getBackingArrayLength());
        
        // Pop most elements but keep some
        for (int i = 0; i < 900; i++) {
            stack.pop();
        }
        
        System.out.println("\nAfter popping 900 elements:");
        System.out.println("Stack size: " + stack.size());
        System.out.println("Backing array length: " + stack.getBackingArrayLength());
        System.out.println("Note: The backing array still holds references to all 1000 objects!");
        
        // Force garbage collection
        System.gc();
        
        // The memory won't be freed because the backing array still holds references
        // to all the objects that were pushed, even though they were popped
    }
    
    private static String createLargeObject(int index) {
        // Create a large string to simulate a large object
        char[] largeArray = new char[1000];
        Arrays.fill(largeArray, 'X');
        return new String(largeArray) + "-" + index;
    }
}

/**
 * A Stack implementation that demonstrates a memory leak.
 * The leak occurs because popped elements are not nullified in the backing array.
 */
class Stack<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(T e) {
        ensureCapacity();
        this.elements[size++] = e;
    }

    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        // Memory leak: We're not nullifying the reference in the array
        return (T) this.elements[--size];
    }

    public int size() {
        return this.size;
    }

    public int getBackingArrayLength() {
        return this.elements.length;
    }

    private void ensureCapacity() {
        if (this.elements.length == this.size) {
            this.elements = Arrays.copyOf(this.elements, 2 * this.size + 1);
        }
    }
}

/**
 * A corrected Stack implementation that prevents memory leaks
 * by nullifying references in the backing array.
 */
class CorrectedStack<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public CorrectedStack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(T e) {
        ensureCapacity();
        this.elements[size++] = e;
    }

    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        // Fix: Nullify the reference in the array
        T result = (T) this.elements[--size];
        this.elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    public int size() {
        return this.size;
    }

    public int getBackingArrayLength() {
        return this.elements.length;
    }

    private void ensureCapacity() {
        if (this.elements.length == this.size) {
            this.elements = Arrays.copyOf(this.elements, 2 * this.size + 1);
        }
    }
} 