package com.example.model;
import java.util.Date;


public class VisaInformation {
	private int id;
    private VisaType visaType;
    private String visaNumber;
    private Date expirationDate;
    private VisaStatus visaStatus;
    
    public VisaInformation() {
    	
    }

    // Constructor
    public VisaInformation(VisaType visaType, String visaNumber, Date expirationDate, VisaStatus visaStatus) {
    	this.visaType = visaType;
        this.visaNumber = visaNumber;
        this.expirationDate = expirationDate;
        this.visaStatus = visaStatus;
    }

    // Getters and Setters


    public String getVisaNumber() {
        return visaNumber;
    }

    public void setVisaNumber(String visaNumber) {
        this.visaNumber = visaNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VisaStatus getVisaStatus() {
		return visaStatus;
	}

	public void setVisaStatus(VisaStatus visaStatus) {
		this.visaStatus = visaStatus;
	}

	public VisaType getVisaType() {
		return visaType;
	}

	public void setVisaType(VisaType visaType) {
		this.visaType = visaType;
	}
}
