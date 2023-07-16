package com.generics.item_29_favor_generic_types;

public class Stack<E> {

    private final Object[] stack;
    private int size;

    public Stack(int size) {
        // cannot use generic type to
        // instantiate "stack"
        this.stack = new Object[size];
        this.size = -1;
    }

    public boolean push(E item) {

        if (stack.length == size) {
            return false;
        }

        stack[size] = item;
        size++;

        return true;
    }

    public E pop() {
        if (size == -1) {
            return null;
        }

        @SuppressWarnings("unchecked")
        // we know only elements of type "E" are added
        // so this cast is okay
        E item = (E) stack[size];

        size--;
        return item;
    }
}
