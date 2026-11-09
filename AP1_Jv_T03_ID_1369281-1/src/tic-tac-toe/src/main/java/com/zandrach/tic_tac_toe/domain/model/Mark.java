package com.zandrach.tic_tac_toe.domain.model;

public enum Mark {
    X(1),
    O(-1),
    NONE(0) {
        @Override
        public String toString() {
            return "None";
        }
    };

    private final int value;

    Mark(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Mark fromValue(int value) {
        for (Mark v : Mark.values()) {
            if (v.value == value) {
                return v;
            }
        }
        throw new IllegalArgumentException("Unknown cell value: " + value);
    }

}
