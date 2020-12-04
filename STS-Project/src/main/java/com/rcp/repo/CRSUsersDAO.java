package com.rcp.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.rcp.model.CRSUsers;
import com.rcp.util.ConnectionUtil;

public class CRSUsersDAO implements DAOContract<CRSUsers, String> {

	@Override
	public int create(CRSUsers t) {
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into crs_users (crs_username, crs_password, user_first_name, user_last_name, user_email, user_role_id) values (?,?,?,?,?,2);";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, t.getUsername());
			stmt.setString(2,t.getPassword());
			stmt.setString(3, t.getUserFirstName());
			stmt.setString(4, t.getUserLastName());
			stmt.setString(5, t.getUserEmail());
			return stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<CRSUsers> findAll() {
		Connection con = ConnectionUtil.getConnection();
		List<CRSUsers> userList = new ArrayList<>();
		String sql = "select * from crs_users_view;"; 
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CRSUsers tempUser = new CRSUsers.CRSUsersBuilder(rs.getString("crs_username"))
						.password(rs.getString("crs_password"))
						.userFirstName(rs.getString("user_first_name"))
						.userLastName(rs.getString("user_last_name"))
						.userEmail(rs.getString("user_email"))
						.userRole(rs.getString("user_role"))
						.build();
				userList.add(tempUser);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public List<CRSUsers> findBy(String i) {
		Connection con = ConnectionUtil.getConnection();
		List<CRSUsers> userList = new ArrayList<>();
		String sql = "select * from crs_users_view where crs_username = ?;"; 
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, i);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CRSUsers tempUser = new CRSUsers.CRSUsersBuilder(rs.getString("crs_username"))
						.password(rs.getString("crs_password"))
						.userFirstName(rs.getString("user_first_name"))
						.userLastName(rs.getString("user_last_name"))
						.userEmail(rs.getString("user_email"))
						.userRole(rs.getString("user_role"))
						.build();
				userList.add(tempUser);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return userList;
	}

	@Override
	public int update(CRSUsers t) {
		Connection con = ConnectionUtil.getConnection();
		int result = 0;
		String sql = "update crs_users set user_role_id = ? where crs_username =?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			switch (t.getUserRole()) {
			case "Finance Manager":
				stmt.setInt(1, 1);
				break;
			case "Client":
				stmt.setInt(1, 2);
				break;
			}
			stmt.setString(2, t.getUsername());
			result = stmt.executeUpdate();				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public int delete(CRSUsers t) {
		Connection con = ConnectionUtil.getConnection();
		int result = 0;
		String sql = "delete from crs_users where crs_username =?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, t.getUsername());
			result = stmt.executeUpdate();				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteBy(String i) {
		Connection con = ConnectionUtil.getConnection();
		int result = 0;
		String sql = "delete from crs_users where crs_username =?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, i);
			result = stmt.executeUpdate();				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
