package com.qlnv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qlnv.model.Role;
import com.qlnv.model.User;
import com.qlnv.util.DBConnection;
import com.qlnv.util.PasswordUtil;

public class UserDao {

	public User findUserByEmail(String email) {
		Connection connection = DBConnection.getConnection();
		String sql = "select * from users where email=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				user.setRole(role);
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public boolean editUser(int id, String name, String email, String password, String address, String phone,
			int roleId) {
		Connection connection = DBConnection.getConnection();
		String sql = "update users set name=?,email=?,password=?,address=?,phone=?,role_id=? where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, PasswordUtil.hashPassword(password));
			ps.setString(4, address);
			ps.setString(5, phone);
			ps.setInt(6, roleId);
			ps.setInt(7, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteUserById(int id) {
		Connection connection = DBConnection.getConnection();
		String sql = "delete from users where id = ?";
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

	public List<User> getListUser() {
		List<User> listUser = new ArrayList<User>();
		Connection connection = DBConnection.getConnection();
		String sql = "select * from users";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				user.setRole(role);
				listUser.add(user);
			}
			return listUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addUser(String name, String email, String password, String address, String phone, int roleId) {
		Connection connection = DBConnection.getConnection();
		String sql = "insert into users(name,email,password,address,phone,role_id) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, PasswordUtil.hashPassword(password));
			ps.setString(4, address);
			ps.setString(5, phone);
			ps.setInt(6, roleId);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
