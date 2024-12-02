package com.qlnv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.qlnv.util.DBConnection;

public class ProjectDao {

	public boolean addProject(String name, String startDate, String endDate, int userId) {
		Connection connection = DBConnection.getConnection();
		String sql = "insert into projects(name,start_date,end_date,user_id) values(?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			ps.setInt(4, userId);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
