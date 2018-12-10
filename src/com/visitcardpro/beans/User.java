package com.visitcardpro.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	@JsonIgnore
	protected long id;
	@JsonIgnore
	protected Authentication auth;

	public User() { }

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