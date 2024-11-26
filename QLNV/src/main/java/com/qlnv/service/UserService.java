package com.qlnv.service;

import com.qlnv.dao.UserDao;

public class UserService {

	private UserDao ud = new UserDao();

	public boolean addUser(String name, String email, String password, String address, String phone, int roleId) {
		return ud.addUser(name, email, password, address, phone, roleId);
	}
}
