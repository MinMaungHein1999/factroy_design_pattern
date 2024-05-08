package com.example.model;

import java.util.Date;

public class RegularStudent extends Student {
	private String nrc;
	
	public RegularStudent(int id, String name, int age, String nrc, boolean is_regular, Date created_at) {
		super(id, name, age, is_regular, created_at);
		this.nrc = nrc;
	}
	
	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
}
