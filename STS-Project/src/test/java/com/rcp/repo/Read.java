package com.rcp.repo;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rcp.model.CRSReimb;
import com.rcp.model.CRSReimbStatus;
import com.rcp.model.CRSReimbType;
import com.rcp.model.CRSUsers;
import com.rcp.model.RockomaxProjects;
import com.rcp.model.RockomaxProjectsStatus;

public class Read {
	CRSUsers t = new CRSUsers.CRSUsersBuilder("hadion")
			.password("hal9000")
			.userFirstName("Daniel")
			.userLastName("Jackson")
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
	public void findAll1UsersTest() {
		
		CRSUsersDAO ud = new CRSUsersDAO();
		List<CRSUsers> l = new ArrayList<>();
		l.add(t);
		assertEquals(l.get(0).getUsername(),ud.findAll().get(0).getUsername());
	}
	@Test
	public void findBy1UsernameTest() {
		CRSUsersDAO ud = new CRSUsersDAO();
		List<CRSUsers> l = new ArrayList<>();
		l.add(t);
		assertEquals(l.get(0).getUsername(),ud.findBy("hadion").get(0).getUsername());
	}
	
	@Test
	public void findAll2ProjectsTest() {
		RockomaxProjectsDAO pd = new RockomaxProjectsDAO();
		List<RockomaxProjects> l = new ArrayList<>();
		l.add(p);
		assertEquals(l.get(0).getProjectName(),pd.findAll().get(0).getProjectName());
	}
	@Test
	public void findBy2ProjectNameTest() {
		RockomaxProjectsDAO pd = new RockomaxProjectsDAO();
		List<RockomaxProjects> l = new ArrayList<>();
		l.add(p);
		assertEquals(l.get(0).getProjectName(),pd.findBy("JoolProbe").get(0).getProjectName());
	}
	@Test
	public void findAll3ReimbTest() {
		CRSReimbDAO rd = new CRSReimbDAO();
		List<CRSReimb> l = new ArrayList<>();
		l.add(r);
		assertEquals(l.get(0).getId(),rd.findAll().get(0).getId());
	}
	@Test
	public void findBy3ReimbIdTest() {
		CRSReimbDAO rd = new CRSReimbDAO();
		List<CRSReimb> l = new ArrayList<>();
		l.add(r);
		assertEquals(l.get(0).getId(),rd.findBy("0").get(0).getId());
		//TODO fix hardcoded id.
	}
}
