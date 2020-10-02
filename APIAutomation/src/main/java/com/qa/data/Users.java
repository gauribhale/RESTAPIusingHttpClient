package com.qa.data;

//POJO: Plain Old Java Object equivalent to JSON
public class Users {

	String name,job,id,createdAt,updatedAt;

	
	public Users(String name,String job) {
		this.name=name;
		this.job=job;
	}

	public Users() {
		
	}
	
	//getters and setters methods

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public String getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
