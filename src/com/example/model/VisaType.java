package com.example.model;

public enum VisaType {
    TOURIST(1),
    WORK(2),
    STUDENT(3),
    BUSINESS(4),
    TRANSIT(5),
    PERMANENT_RESIDENCY(6),
    SPOUSE_OR_FAMILY(7),
    INVESTOR(8);

    private final int value;

    VisaType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static VisaType fromValue(int value) {
        for (VisaType type : VisaType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Visa Type value: " + value);
    }
}
