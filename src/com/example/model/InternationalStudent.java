package com.example.model;

import java.util.Date;

public class InternationalStudent extends Student {
	private VisaInformation visaInformation;
	private ImmigrationStatus immigrationStatus;
	
	public InternationalStudent(int id, String name, int age, boolean is_regular, Date created_at, VisaInformation visaInformation, ImmigrationStatus immigrationStatus) {
		super(id, name, age, is_regular, created_at);

		this.visaInformation = visaInformation;
		this.immigrationStatus = immigrationStatus;
	}

	public VisaInformation getVisaInformation() {
		return visaInformation;
	}

	public void setVisaInformation(VisaInformation visaInformation) {
		this.visaInformation = visaInformation;
	}

	public ImmigrationStatus getImmigrationStatus() {
		return immigrationStatus;
	}

	public void setImmigrationStatus(ImmigrationStatus immigrationStatus) {
		this.immigrationStatus = immigrationStatus;
	}
	
}
