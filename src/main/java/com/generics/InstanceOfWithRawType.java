package com.generics;

import java.util.Set;

public class InstanceOfWithRawType {

    static Object demoInstanceof(Object o) {
        // instanceof operator does not work with generic type
        if (o instanceof Set) {
            // using bounded wildcard type ensures type-safety
            Set<?> typeSafeO = (Set<?>) o;
            return typeSafeO;
        }
        return null;
    }
}
