package com.example.model;

public enum VisaStatus {
	 	ACTIVE(1),
	    EXPIRED(2),
	    PENDING(3),
	    CANCELLED(4),
	    UNKNOWN(0);

	    private int value;

	    VisaStatus(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }

	    public static VisaStatus fromValue(int value) {
	        for (VisaStatus status : VisaStatus.values()) {
	            if (status.value == value) {
	                return status;
	            }
	        }
	        return UNKNOWN;
	    }
}
