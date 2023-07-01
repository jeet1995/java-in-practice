package com.enums;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnumMapInsteadOfOrdinal {

    // Segregating Plant instances based on lifecycle
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();

        // 1. constructor uses a bounded type token
        // 2. a bad alternative to EnumMap is use Set<Plant>[] as a data structure
        // where we rely on the ordinality of the Lifecyle enum type - can lead to
        // ArrayIndexOutOfBounds or incorrect ordinality
        EnumMap<Plant.Lifecycle, Set<Plant>> plantByLifecycleMap = new EnumMap<>(Plant.Lifecycle.class);

        for (Plant plant : plants) {
            plantByLifecycleMap.put(plant.getLifecycle(), new HashSet<>());
            plantByLifecycleMap.get(plant.getLifecycle()).add(plant);
        }
    }
}

class Plant {
    public enum Lifecycle {ANNUAL, PERENNIAL, BIENNIAL}

    private Lifecycle lifecycle;

    public Lifecycle getLifecycle() {
        return this.lifecycle;
    }
}
