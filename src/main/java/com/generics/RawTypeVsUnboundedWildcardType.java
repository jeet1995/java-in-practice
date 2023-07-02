package com.generics;

import java.util.Set;

public class RawTypeVsUnboundedWildcardType {

    // an element of any type can be added to a raw type
    static int countCommonWithRawType(Set set1, Set set2) {
        int result = 0;
        for (Object o : set1) {
            if (set2.contains(o)) {
                result++;
            }
        }
        return result;
    }

    // you can't add an element of any type
    static int countCommonWithUnboundedWildcardType(Set<?> set1, Set<?> set2) {
        int result = 0;
        for (Object o : set1) {
            if (set2.contains(o)) {
                result++;
            }
        }
        return result;
    }
}
