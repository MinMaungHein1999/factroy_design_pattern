package com.example.model;

import java.util.Date;

public abstract class Student {
	private int id;
	private String name;
	private int age;
	private boolean isRegular;
	private Date createdAt;
	
	
	public Student(int id, String name, int age, boolean isRegular, Date createdAt) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.isRegular = isRegular;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isRegular() {
		return isRegular;
	}

	public void setRegular(boolean isRegular) {
		this.isRegular = isRegular;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	


}
