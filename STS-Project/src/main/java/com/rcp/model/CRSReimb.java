package com.rcp.model;

import java.io.File;
import java.sql.Timestamp;

public class CRSReimb {
	
	private int id;
	private RockomaxProjects project;
	private double reimbAmmount;
	private Timestamp reimbSubmitted;
	private Timestamp reimbResolved;
	private String reimbDescription;
	private File recipt;
	private CRSUsers client;
	private CRSUsers resolver;
	private CRSReimbStatus reimbStatus;
	private CRSReimbType reimbType;

	
	private CRSReimb(CRSReimbBuilder builder) {
		this.id = builder.id;
		this.reimbAmmount = builder.ammount;
		this.reimbSubmitted = builder.submitted;
		this.reimbResolved = builder.resolved;
		this.reimbDescription = builder.description;
		this.recipt = builder.recipt;
		this.client = builder.client;
		this.resolver = builder.resolver;
		this.reimbStatus = builder.status;
		this.reimbType = builder.type;
		this.project = builder.project;
	}
	

	public double getReimbAmmount() {
		return reimbAmmount;
	}
	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}
	public Timestamp getReimbResolved() {
		return reimbResolved;
	}
	public String getReimbDescription() {
		return reimbDescription;
	}
	public File getRecipt() {
		return recipt;
	}
	
	public CRSUsers getResolver() {
		return resolver;
	}


	public RockomaxProjects getProject() {
		return project;
	}


	public CRSUsers getClient() {
		return client;
	}


	public CRSReimbStatus getReimbStatus() {
		return reimbStatus;
	}


	public CRSReimbType getReimbType() {
		return reimbType;
	}


	public int getId() {
		return id;
	}
	public static class CRSReimbBuilder {
		
		private int id;
		private double ammount;
		private Timestamp submitted;
		private Timestamp resolved;
		private String description;
		private File recipt;
		private CRSUsers client;
		private CRSUsers resolver;
		private RockomaxProjects project;
		private CRSReimbType type;
		private CRSReimbStatus status;
	
		
		public CRSReimbBuilder id(int id) {
			this.id = id;
			return this;
		}
		public CRSReimbBuilder client(CRSUsers client) {
			this.client = client;
			return this;
		}
		public CRSReimbBuilder resolver(CRSUsers financeManger) {
			this.resolver = financeManger;
			return this;
		}
		public CRSReimbBuilder project(RockomaxProjects project) {
			this.project = project;
			return this;
		}
		public CRSReimbBuilder type(CRSReimbType type) {
			this.type = type;
			return this;
		}
		public CRSReimbBuilder status(CRSReimbStatus status) {
			this.status = status;
			return this;
		}
		public CRSReimbBuilder ammount(double ammount) {
			this.ammount = ammount;
			return this;
		}
		public CRSReimbBuilder submitted(Timestamp submitted) {
			this.submitted = submitted;
			return this;
		}
		public CRSReimbBuilder resolved(Timestamp resolved) {
			this.resolved = resolved;
			return this;
		}
		public CRSReimbBuilder description(String description) {
			this.description = description;
			return this;
		}
		public CRSReimbBuilder recipt(File recipt) {
			this.recipt = recipt;
			return this;
		}
		public CRSReimb build() {
			CRSReimb reimb = new CRSReimb(this);
			return validateReimbObject(reimb);
		}
		public CRSReimb validateReimbObject(CRSReimb reimb) {
			//TODO  Do some basic Validations to check if reimb object does not
			//		violate any assumptions of the system
			return reimb;
		}
	}
	


	

}	
