package com.enums;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CapturingEnumTransitionUsingEnumMap {
}

enum State {
    SOLID, LIQUID, GAS;

    public enum Transition {

        SOLIDIFICATION(LIQUID, SOLID),
        SUBLIMATION(SOLID, GAS),
        VAPORIZATION(LIQUID, GAS);

        private final State from;
        private final State to;

        Transition(State from, State to) {
            this.from = from;
            this.to = to;
        }

        // Ex: LIQUID -> {SOLID -> FREEZING, GAS -> VAPORIZATION}
        private static final Map<State, Map<State, Transition>> transitionsByState = Stream
                .of(Transition.values())
                .collect(
                        Collectors.groupingBy(
                                // outer map will map "from" State to a nested map
                                t -> t.from,
                                () -> new EnumMap<>(State.class),
                                // nested map will map "to" State to some Transition type
                                Collectors.toMap(
                                        // collect all keys based "to" value
                                        t -> t.to,
                                        // map some Transition type associated with "to" value
                                        t -> t,
                                        // merge strategy is to overwrite previously mapped value
                                        (x, y) -> y,
                                        // how the map should be stored
                                        () -> new EnumMap<>(State.class)
                                )
                        )
                );

    }

}
