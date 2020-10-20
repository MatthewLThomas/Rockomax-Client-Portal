package com.rcp.model;

import java.io.File;

public class RockomaxProjects {
		private int projectId;
		private String projectName;
		private double projectLauncVehicleCost;
		private double projectPayloadCost;
		private String projectDescription;
		private CRSUsers client;
		private RockomaxProjectStatus project_status;
		private File craftFile;//for Payload
		private File payloadImage;
		private File LauncherImage;
		
		private RockomaxProjects(RockomaxProjectsBuilder builder) {
			this.projectId = builder.Id;
			this.projectName = builder.name;
			this.projectLauncVehicleCost = builder.launchVehicleCost;
			this.projectPayloadCost = builder.payloadCost;
			this.projectDescription = builder.description;
			this.client = builder.client;
			this.project_status = builder.status;
			this.craftFile = builder.craftFile;
			this.payloadImage = builder.payloadImage;
			this.LauncherImage = builder.launcherImage;
		}
		
		public int getProjectId() {
			return projectId;
		}
		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public double getProjectLauncVehicleCost() {
			return projectLauncVehicleCost;
		}
		public void setProjectLauncVehicleCost(double projectLauncVehicleCost) {
			this.projectLauncVehicleCost = projectLauncVehicleCost;
		}
		public double getProjectPayloadCost() {
			return projectPayloadCost;
		}
		public void setProjectPayloadCost(double projectPayloadCost) {
			this.projectPayloadCost = projectPayloadCost;
		}
		public String getProjectDescription() {
			return projectDescription;
		}
		public void setProjectDescription(String projectDiscription) {
			this.projectDescription = projectDiscription;
		}
		public CRSUsers getClient() {
			return client;
		}
		public void setClient(CRSUsers client) {
			this.client = client;
		}
		public RockomaxProjectStatus getProject_status() {
			return project_status;
		}
		public void setProject_status(RockomaxProjectStatus project_status) {
			this.project_status = project_status;
		}
		public File getCraftFile() {
			return craftFile;
		}
		public void setCraftFile(File craftFile) {
			this.craftFile = craftFile;
		}
		public File getPayloadImage() {
			return payloadImage;
		}
		public void setPayloadImage(File payloadImage) {
			this.payloadImage = payloadImage;
		}
		public File getLauncherImage() {
			return LauncherImage;
		}
		public void setLauncherImage(File launcherImage) {
			LauncherImage = launcherImage;
		}
		
		public class RockomaxProjectsBuilder {
			public RockomaxProjectStatus status;
			public String description;
			public double launchVehicleCost;
			public int Id = 0;
			public String name;
			public double payloadCost;
			public CRSUsers client;
			public File craftFile;//for Payload
			public File payloadImage;
			public File launcherImage;
			
			public RockomaxProjectsBuilder(int Id) {
				this.Id = Id;
			}
			public RockomaxProjectsBuilder status (RockomaxProjectStatus status) {
				this.status = status;
				return this;
			}
			public RockomaxProjectsBuilder description (String description) {
				this.description = description;
				return this;
			}
			public RockomaxProjectsBuilder launchVehicleCost(double launchVehicleCost) {
				this.launchVehicleCost = launchVehicleCost;
				return this;
			}
			public RockomaxProjectsBuilder name (String name) {
				this.name = name;
				return this;
			}
			public RockomaxProjectsBuilder payloadCost(double payloadCost) {
				this.payloadCost = payloadCost;
				return this;
			}
			public RockomaxProjectsBuilder client(CRSUsers client) {
				this.client = client;
				return this;
			}
			public RockomaxProjectsBuilder craftFile(File craftFile) {
				this.craftFile = craftFile;
				return this;
			}
			public RockomaxProjectsBuilder payloadImage(File payloadImage) {
				this.payloadImage = payloadImage;
				return this;
			}
			public RockomaxProjects build() {
				RockomaxProjects r = new RockomaxProjects(this);
				return validateRockomaxProjects(r);
			}
			private RockomaxProjects validateRockomaxProjects(RockomaxProjects r) {
				// TODO Do some basic Validations to check if RockomaxProjects object does not
				//		violate any assumptions of the system
				return r;
			}
		}
}
