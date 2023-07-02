package com.enums;

import java.util.Arrays;
import java.util.Collection;

public class MimicExtensibleEnumWithAnInterface {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);

        // pass class literal (bounded token type) to obtain all enum constants
        testAllOpsWithBoundedTokenType(BasicOperation.class, x, y);
        testAllOpsWithBoundedTokenType(ExtendedOperation.class, x, y);

        // pass enum constants in a list
        testAllOpsWithBoundedWildcardType(Arrays.asList(BasicOperation.values()), x, y);
    }

    // 1. "enumType" should represent both an enum and also a subtype of Operation
    private static <T extends Enum<T> & Operation> void testAllOpsWithBoundedTokenType(Class<T> enumType, double x, double y) {
        for (Operation op : enumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }

    // 2. this method looks simpler but loses the capability of making use of an EnumMap or an EnumSet
    private static void testAllOpsWithBoundedWildcardType(Collection<? extends Operation> ops, double x, double y) {
        for (Operation op : ops) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}

interface Operation {
    double apply(double x, double y);
}

enum BasicOperation implements Operation {

    ADD("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    }, MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    }, MULTIPLY("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    }, DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String opCode;

    BasicOperation(String opCode) {
        this.opCode = opCode;
    }

    @Override
    public String toString() {
        return opCode;
    }
}

enum ExtendedOperation implements Operation {
    REMAINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String opCode;

    ExtendedOperation(String opCode) {
        this.opCode = opCode;
    }

    @Override
    public String toString() {
        return opCode;
    }
}
