package com.rcp.model;

public class CRSUsers {

	private int id;
	private String username;
	private String password;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userRole;
	
	private CRSUsers(CRSUsersBuilder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.userFirstName = builder.userFirstName;
		this.userLastName = builder.userLastName;
		this.userRole = builder.userRole;
		this.userEmail = builder.userEmail;
		this.id = builder.id;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public String getUserRole() {
		return userRole;
	}
	public int getId() {
		return id;
	}
	public static class CRSUsersBuilder {
		private int id;
		private String username;
		private String password;
		private String userFirstName;
		private String userLastName;
		private String userEmail;
		private String userRole;
		public CRSUsersBuilder(String username) {
			this.username = username;
		}
		public CRSUsersBuilder id(int id) {
			this.id = id;
			return this;
		}
		public CRSUsersBuilder password(String password) {
			this.password = password;
			return this;
		}
		public CRSUsersBuilder userFirstName(String userFirstName) {
			this.userFirstName=userFirstName;
			return this;
		}
		public CRSUsersBuilder userLastName(String userLastName) {
			this.userLastName = userLastName;
			return this;
		}
		public CRSUsersBuilder userEmail(String userEmail) {
			this.userEmail = userEmail;
			return this;
		}
		public CRSUsersBuilder userRole(String userRole) {
			this.userRole = userRole;
			return this;
		}
		public CRSUsers build() {
			CRSUsers u = new CRSUsers(this);
			return validateCRSUsers(u);
		}
		private CRSUsers validateCRSUsers(CRSUsers u) {
			//TODO: Do some basic Validations to check if CRSUsers u
			//			violates any assumptions of the system.
			return u;
		}
	}
	
	
}
