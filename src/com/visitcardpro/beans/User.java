package com.visitcardpro.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	@JsonIgnore
	protected long id;
	@JsonIgnore
	protected Authentication auth;
	protected String firstName;
	protected String lastName;

	public User() { }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Authentication getAuth() {
		return auth;
	}

	public void setAuth(Authentication auth) {
		this.auth = auth;
	}
}