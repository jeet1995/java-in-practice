package com.generics.item_28_generics_over_arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomChooser<T> {

    // having an array T[] here would introduce
    // possible ClassCastException scenarios
    private final List<T> list;

    public RandomChooser(List<T> list) {
        // creating a local copy is good practice to guard against side-effects
        this.list = new ArrayList<>(list);
    }

    public T getNextObject() {
        int index = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(index);
    }
}
