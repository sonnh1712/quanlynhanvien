package com.qlnv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qlnv.model.Role;
import com.qlnv.util.DBConnection;

public class RoleDao {

	public boolean deleteRoleById(int id) {
		Connection connection = DBConnection.getConnection();
		String sql = "delete from roles where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean editRole(int id, String name, String des) {
		Connection connection = DBConnection.getConnection();
		String sql = "update roles set name=?,des=? where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, des);
			ps.setInt(3, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public Role findRoleById(int id) {
		Connection connection = DBConnection.getConnection();
		String sql = "select * from roles where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Role r = new Role();
				r.setId(rs.getInt("id"));
				r.setName(rs.getString("name"));
				r.setDes(rs.getString("des"));
				return r;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public List<Role> getListRole() {
		List<Role> list = new ArrayList<Role>();
		Connection connection = DBConnection.getConnection();
		String sql = "select * from roles";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Role r = new Role();
				r.setId(rs.getInt("id"));
				r.setName(rs.getString("name"));
				r.setDes(rs.getString("des"));
				list.add(r);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public boolean addRole(String name, String des) {
		Connection connection = DBConnection.getConnection();
		String sql = "insert into roles(name,des) values(?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, des);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
