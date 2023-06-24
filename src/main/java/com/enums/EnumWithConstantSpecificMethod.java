package com.enums;

public enum EnumWithConstantSpecificMethod {

    ADD {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    DIVIDE {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    // better than using switch cases to map a constant
    // to some functionality especially when each constant
    // share an abstraction - the "apply" contract is enforced
    // on each enum-type
    public abstract double apply(double x, double y);
}
