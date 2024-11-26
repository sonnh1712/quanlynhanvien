package com.qlnv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.qlnv.util.DBConnection;
import com.qlnv.util.PasswordUtil;

public class UserDao {

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
