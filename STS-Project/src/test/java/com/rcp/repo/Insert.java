package com.rcp.repo;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

import com.rcp.model.CRSReimb;
import com.rcp.model.CRSReimbStatus;
import com.rcp.model.CRSReimbType;
import com.rcp.model.CRSUsers;
import com.rcp.model.RockomaxProjects;
import com.rcp.model.RockomaxProjectsStatus;

public class Insert {
	
	CRSUsers t = new CRSUsers.CRSUsersBuilder("ProbodobodyneCorp")
			.password("hal9000")
			.userFirstName("Daniel J.")
			.userLastName("Kerman")
			.userEmail("pyramids@arespaceships.net")
			.build();
	RockomaxProjectsStatus s = new RockomaxProjectsStatus();
	Timestamp d = Timestamp.valueOf(LocalDateTime.of(2020,Month.OCTOBER,20,15,45));
	CRSReimbStatus st = new CRSReimbStatus();
	CRSReimbType ty = new CRSReimbType();
	{
	s.setStatusId(1);
	s.setStatus("In-Development");
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
			.description("Our rideshare was lost during flight 6 with the catistrophic loss of Gemini-II SN:016")
			.client(t)
			.status(st)
			.type(ty)
			.project(p)
			.build();
	
	@Test
	public void create1UserTest() {
		CRSUsersDAO ud = new CRSUsersDAO();
		
		assertEquals(1,ud.create(t));
	}
	@Test
	public void create2ProjectTest() {
		RockomaxProjectsDAO pd = new RockomaxProjectsDAO();
		
		assertEquals(1,pd.create(p));
		
	}
	@Test
	public void create3ReimbTest() {
		CRSReimbDAO rd = new CRSReimbDAO();
		assertEquals(1,rd.create(r));
	}

}