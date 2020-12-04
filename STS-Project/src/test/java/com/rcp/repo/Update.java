package com.rcp.repo;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;

import com.rcp.model.CRSReimb;
import com.rcp.model.CRSReimbStatus;
import com.rcp.model.CRSReimbType;
import com.rcp.model.CRSUsers;
import com.rcp.model.RockomaxProjects;
import com.rcp.model.RockomaxProjectsStatus;

public class Update {
	CRSUsers t = new CRSUsers.CRSUsersBuilder("hadion")
			.password("hal9000")
			.userFirstName("Daniel")
			.userLastName("Jackson")
			.userEmail("pyramids@arespaceships.net")
			.build();
	CRSUsers t2 = new CRSUsers.CRSUsersBuilder("RockomaxBilly")
			.password("hal9000")
			.userFirstName("Billy")
			.userLastName("Kerman")
			.userEmail("pyramids@arespaceships.net")
			.build();
	RockomaxProjectsStatus s = new RockomaxProjectsStatus();
	Timestamp d = Timestamp.valueOf(LocalDateTime.of(2020,Month.OCTOBER,20,15,45));
	Timestamp d2 = Timestamp.valueOf(LocalDateTime.of(2020,Month.OCTOBER,25,10,45));
	CRSReimbStatus st = new CRSReimbStatus();
	CRSReimbType ty = new CRSReimbType();
	{
	s.setStatusId(0);
	s.setStatus("Finished");
	st.setId(1);
	st.setStatus("Pending");
	ty.setId(1);
	ty.setType("Payload");
	}
	RockomaxProjects p = new RockomaxProjects.RockomaxProjectsBuilder()
			.name("JoolProbe")
			.launchVehicleCost(20000.00)
			.payloadCost(10000.00)
			.description("A small science probe to the Jool system.")
			.client(t)
			.status(s)
			.build();
	CRSReimb r = new CRSReimb.CRSReimbBuilder()
			.id(0)
			.ammount(10000.00)
			.submitted(d)
			.resolved(d2)
			.description("Our rideshare was lost during flight 6 with the catistrophic loss of Gemini-II SN:016")
			.client(t)
			.resolver(t2)
			.status(st)
			.type(ty)
			.project(p)
			.build();
	@BeforeClass
	public void setup() {
		CRSUsersDAO ud = new CRSUsersDAO();
		ud.create(t2);
		
	}
	
	@Test
	public void update1UserTest() {
		CRSUsersDAO ud = new CRSUsersDAO();
		CRSUsers t2 = new CRSUsers.CRSUsersBuilder("hadion")
				.password("hal9000")
				.userFirstName("Daniel")
				.userLastName("Jackson")
				.userEmail("pyramids@arespaceships.net")
				.userRole("Finance Manager")
				.build();	
		assertEquals(1,ud.update(t2));
	}
	@Test
	public void update1ProjectTest() {
		RockomaxProjectsDAO pd = new RockomaxProjectsDAO();
		assertEquals(1,pd.update(p));
		
		
	}
}
