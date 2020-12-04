package com.rcp.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rcp.model.CRSReimb;
import com.rcp.model.CRSReimbStatus;
import com.rcp.model.CRSReimbType;
import com.rcp.model.CRSUsers;
import com.rcp.model.RockomaxProjects;
import com.rcp.util.ConnectionUtil;
import com.rcp.util.FileOpp;

public class CRSReimbDAO implements DAOContract<CRSReimb, String>{

	@Override
	public int create(CRSReimb t) {
		String sql = "insert into CRS_Reimb (reimb_project,reimb_ammount,reimb_submitted,reimb_resolved,reimb_description,reimb_recipt,reimb_author,reimb_resolver,reimb_status_id,reimb_type_id) values (?,?,?,?,?,?,?,?,?,?)";
		try(Connection con = ConnectionUtil.getConnection();PreparedStatement stmt = con.prepareStatement(sql) ){
			stmt.setInt(1, t.getProject().getProjectId());
			stmt.setDouble(2, t.getReimbAmmount());
			stmt.setTimestamp(3, t.getReimbSubmitted());
			stmt.setTimestamp(4, t.getReimbResolved());
			stmt.setString(5, t.getReimbDescription());
			stmt.setBytes(6,FileOpp.fileToByte(t.getRecipt()));
			stmt.setInt(7,t.getClient().getId());
			stmt.setInt(8,t.getResolver().getId());
			stmt.setInt(9, t.getReimbStatus().getId());
			stmt.setInt(10, t.getReimbType().getId());
			return stmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<CRSReimb> findAll() {
		List<CRSReimb> l = new ArrayList<>();
		String sql = "select * from reimb_view_resolved;";
		try(Connection con = ConnectionUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CRSReimbStatus s = new CRSReimbStatus();
				s.setId(rs.getInt(17));
				s.setStatus(rs.getString(18));
				CRSReimbType t = new CRSReimbType();
				t.setId(rs.getInt(19));
				t.setType(rs.getString(20));
				CRSReimb r = new CRSReimb.CRSReimbBuilder()
						.id(rs.getInt("reimb_id"))
						.project(new RockomaxProjects.RockomaxProjectsBuilder()
								.id(rs.getInt("p.project_id"))
								.name(rs.getString("p.project_name"))
								.build())
						.ammount(rs.getDouble("reimb_ammount"))
						.submitted(rs.getTimestamp(5))
						.resolved(rs.getTimestamp(6))
						.description(rs.getString(7))
						.recipt(FileOpp.byteToFile(rs.getBytes(8),"recipt.png"))
						.client(new CRSUsers.CRSUsersBuilder(rs.getString(10))
								.id(rs.getInt(9))
								.userFirstName(rs.getString(11))
								.userLastName(rs.getString(12))
								.build())
						.resolver(new CRSUsers.CRSUsersBuilder(rs.getString(14))
								.id(rs.getInt(13))
								.userFirstName(rs.getString(15))
								.userLastName(rs.getString(16))
								.build())
						.status(s)
						.type(t)
						.build();
				l.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<CRSReimb> findBy(String i) {
		List<CRSReimb> l = new ArrayList<>();
		String sql = "select * from reimb_view_resolved where id = ?;";
		try(Connection con = ConnectionUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, i);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CRSReimbStatus s = new CRSReimbStatus();
				s.setId(rs.getInt(17));
				s.setStatus(rs.getString(18));
				CRSReimbType t = new CRSReimbType();
				t.setId(rs.getInt(19));
				t.setType(rs.getString(20));
				CRSReimb r = new CRSReimb.CRSReimbBuilder()
						.id(rs.getInt("reimb_id"))
						.project(new RockomaxProjects.RockomaxProjectsBuilder()
								.id(rs.getInt("p.project_id"))
								.name(rs.getString("p.project_name"))
								.build())
						.ammount(rs.getDouble("reimb_ammount"))
						.submitted(rs.getTimestamp(5))
						.resolved(rs.getTimestamp(6))
						.description(rs.getString(7))
						.recipt(FileOpp.byteToFile(rs.getBytes(8),"recipt.png"))
						.client(new CRSUsers.CRSUsersBuilder(rs.getString(10))
								.id(rs.getInt(9))
								.userFirstName(rs.getString(11))
								.userLastName(rs.getString(12))
								.build())
						.resolver(new CRSUsers.CRSUsersBuilder(rs.getString(14))
								.id(rs.getInt(13))
								.userFirstName(rs.getString(15))
								.userLastName(rs.getString(16))
								.build())
						.status(s)
						.type(t)
						.build();
				l.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public int update(CRSReimb t) {
	
		return 0;
	}

	@Override
	public int delete(CRSReimb t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBy(String i) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

}
