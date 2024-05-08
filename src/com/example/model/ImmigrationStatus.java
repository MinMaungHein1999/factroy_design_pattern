package com.example.model;

public enum ImmigrationStatus {
    F1_STUDENT_VISA(1),
    J1_EXCHANGE_VISITOR_VISA(2),
    H1B_VISA(3),
    OPT_STATUS(4),
    CPT_STATUS(5),
    ASYLUM_SEEKER_REFUGEE_STATUS(6),
    PERMANENT_RESIDENT_STATUS(7);

    private final int value;

    ImmigrationStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ImmigrationStatus fromValue(int value) {
        for (ImmigrationStatus status : ImmigrationStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Immigration Status value: " + value);
    }
}
