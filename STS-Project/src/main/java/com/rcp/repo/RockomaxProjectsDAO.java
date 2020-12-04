package com.rcp.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rcp.model.CRSUsers;
import com.rcp.model.RockomaxProjects;
import com.rcp.model.RockomaxProjectsStatus;
import com.rcp.util.ConnectionUtil;
import com.rcp.util.FileOpp;

public class RockomaxProjectsDAO implements DAOContract<RockomaxProjects, String> {

	@Override
	public int create(RockomaxProjects t) {
		String sql = "insert into rockomax_projects (project_name, project_lv_costs, project_pl_costs, project_description, project_client, project_status, craft_file, payload_image, launcher_image) values (?,?,?,?,?,?,?,?,?)";
		try(Connection con = ConnectionUtil.getConnection();PreparedStatement stmt = con.prepareStatement(sql) ){
			stmt.setString(1, t.getProjectName());
			stmt.setDouble(2, t.getProjectLauncVehicleCost());
			stmt.setDouble(3, t.getProjectPayloadCost());
			stmt.setString(4, t.getProjectDescription());
			stmt.setInt(5, t.getClient().getId());
			stmt.setInt(6,t.getProject_status().getStatusId());
			stmt.setBytes(7,FileOpp.fileToByte(t.getCraftFile()));
			stmt.setBytes(8,FileOpp.fileToByte(t.getPayloadImage()));
			stmt.setBytes(9,FileOpp.fileToByte(t.getLauncherImage()));
			return stmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<RockomaxProjects> findAll() {
		List<RockomaxProjects> l = new ArrayList<>();
		String sql = "select * from projects_view";
		try(Connection con = ConnectionUtil.getConnection();PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				RockomaxProjectsStatus stat = new RockomaxProjectsStatus();
				stat.setStatusId(rs.getInt("project_status_id"));
				stat.setStatus(rs.getString("project_status"));
				RockomaxProjects  r= new RockomaxProjects.RockomaxProjectsBuilder()
						.id(rs.getInt("project_id"))
						.name(rs.getString("project_name"))
						.launchVehicleCost(rs.getDouble("project_lv_costs"))
						.payloadCost(rs.getDouble("project_pl_costs"))
						.description(rs.getString("project_description"))
						.client(new CRSUsers.CRSUsersBuilder(rs.getString("crs_username"))
								.id(rs.getInt("crs_user_id"))
								.userFirstName(rs.getString("user_first_name"))
								.userLastName(rs.getString("user_last_name"))
								.userEmail(rs.getString("user_email"))
								.build())
						.status(stat)
						.craftFile(FileOpp.byteToFile(rs.getBytes("craft_file"), "craft.craft"))
						.payloadImage(FileOpp.byteToFile(rs.getBytes("payload_image"), "payload.png"))
						.launcherImage(FileOpp.byteToFile(rs.getBytes("launcher_image"), "lanchVehicle.png"))
						.build();
				l.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<RockomaxProjects> findBy(String i) {
		List<RockomaxProjects> l = new ArrayList<>();
		String sql = "select * from projects_view where project_name = ?";
		try(Connection con = ConnectionUtil.getConnection();PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, i);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				RockomaxProjectsStatus stat = new RockomaxProjectsStatus();
				stat.setStatusId(rs.getInt("project_status_id"));
				stat.setStatus(rs.getString("project_status"));
				RockomaxProjects  r= new RockomaxProjects.RockomaxProjectsBuilder()
						.id(rs.getInt("project_id"))
						.name(rs.getString("project_name"))
						.launchVehicleCost(rs.getDouble("project_lv_costs"))
						.payloadCost(rs.getDouble("project_pl_costs"))
						.description(rs.getString("project_description"))
						.client(new CRSUsers.CRSUsersBuilder(rs.getString("crs_username"))
								.id(rs.getInt("crs_user_id"))
								.userFirstName(rs.getString("user_first_name"))
								.userLastName(rs.getString("user_last_name"))
								.userEmail(rs.getString("user_email"))
								.build())
						.status(stat)
						.craftFile(FileOpp.byteToFile(rs.getBytes("craft_file"), "craft.craft"))
						.payloadImage(FileOpp.byteToFile(rs.getBytes("payload_image"), "payload.png"))
						.launcherImage(FileOpp.byteToFile(rs.getBytes("launcher_image"), "lanchVehicle.png"))
						.build();
				l.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public int update(RockomaxProjects t) {
		String sql = "update rockomax_projects where project_name = ?;";
		try(Connection con = ConnectionUtil.getConnection();PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, t.getProjectName());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(RockomaxProjects t) {
		
		int result = 0;
		String sql = "delete from rockomax_projects where project_name =?";
		try (Connection con = ConnectionUtil.getConnection();PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, t.getProjectName());
			result = stmt.executeUpdate();				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteBy(String i) {
		int result = 0;
		String sql = "delete from rockomax_projects where project_name =?";
		try (Connection con = ConnectionUtil.getConnection();PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, i);
			result = stmt.executeUpdate();				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
