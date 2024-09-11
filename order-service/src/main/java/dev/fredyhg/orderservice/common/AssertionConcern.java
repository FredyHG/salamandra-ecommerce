package dev.fredyhg.orderservice.common;

import java.util.List;

public class AssertionConcern {
    protected AssertionConcern() {
        super();
    }

    public static void assertArgumentNotEmpty(String aString, String aMessage) {
        if (aString == null || aString.trim().isEmpty()) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    public static void assertListNotEmpty(List aList, String aMessage) {
        if (aList.isEmpty()) {
            throw new IllegalStateException(aMessage);
        }
    }

    public static void assertIntegerGreaterThanOne(Integer value, String aMessage) {
        if (value == null || value <= 1) {
            throw new IllegalStateException(aMessage);
        }
    }
}
