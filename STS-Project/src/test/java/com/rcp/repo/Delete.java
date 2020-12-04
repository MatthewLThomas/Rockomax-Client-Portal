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

public class Delete {
	static CRSUsers t = new CRSUsers.CRSUsersBuilder("hadion")
			.password("hal9000")
			.userFirstName("Daniel")
			.userLastName("Jackson")
			.userEmail("pyramids@arespaceships.net")
			.build();
	static CRSUsers t2 = new CRSUsers.CRSUsersBuilder("hadion2")
			.password("hal9000")
			.userFirstName("Daniel")
			.userLastName("Jackson")
			.userEmail("pyramids@arespaceships.net")
			.build();
	static RockomaxProjectsStatus s = new RockomaxProjectsStatus();
	static Timestamp d = Timestamp.valueOf(LocalDateTime.of(2020,Month.OCTOBER,20,15,45));
	static CRSReimbStatus st = new CRSReimbStatus();
	static CRSReimbType ty = new CRSReimbType();
	{
	s.setStatusId(1);
	s.setStatus("In-Development");
	st.setId(1);
	st.setStatus("Pending");
	ty.setId(1);
	ty.setType("Payload");
	}
	
	static RockomaxProjects p = new RockomaxProjects.RockomaxProjectsBuilder()
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
	static CRSReimb r2 = new CRSReimb.CRSReimbBuilder()
			
			.ammount(10000.00)
			.submitted(d)
			.description("Our rideshare was lost during flight 6 with the catistrophic loss of Gemini-II SN:016")
			.client(t)
			.status(st)
			.type(ty)
			.project(p)
			.build();
	static RockomaxProjectsStatus s2 = new RockomaxProjectsStatus();
	{
	s.setStatusId(0);
	s.setStatus("Finished");
	}
	static RockomaxProjects p2 = new RockomaxProjects.RockomaxProjectsBuilder()
			.name("EelooProbe")
			.launchVehicleCost(30000.00)
			.payloadCost(10000.00)
			.description("A small science probe to Eeloo.")
			.client(t)
			.status(s2)
			.build();
	
	@Test
	public void delete3UserTest() {
		CRSUsersDAO ud = new CRSUsersDAO();
		assertEquals(1,ud.delete(t));
	}
	@BeforeClass
	public static void  setup() {
		CRSUsersDAO ud = new CRSUsersDAO();
		ud.create(t2);
		RockomaxProjectsDAO pd = new RockomaxProjectsDAO();
		pd.create(p2);
		CRSReimbDAO rd = new CRSReimbDAO();
		st.setId(1);
		st.setStatus("Pending");
		ty.setId(1);
		ty.setType("Payload");
		rd.create(r2);
		
		}
	@Test
	public void deleteBy3UsernameTest() {
		CRSUsersDAO ud = new CRSUsersDAO();
		assertEquals(1,ud.deleteBy("hadion2"));
	}
	@Test
	public void delete2ProjectTest() {
		RockomaxProjectsDAO pd = new RockomaxProjectsDAO();
		assertEquals(1,pd.delete(p2));
	}
	@Test
	public void deleteBy2ProjectNameTest() {
		RockomaxProjectsDAO pd = new RockomaxProjectsDAO();
		assertEquals(1,pd.deleteBy("EelooProbe"));
	}
	@Test
	public void delete1ReimbTest() {
		CRSReimbDAO rd = new CRSReimbDAO();
		assertEquals(1,rd.delete(r2));
	}
	@Test 
	public void deleteBy1ReimbIDTest() {
		CRSReimbDAO rd = new CRSReimbDAO();
		assertEquals(1,rd.deleteBy("0"));
	}
}
