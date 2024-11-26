package com.qlnv.service;

import java.util.List;

import com.qlnv.dao.UserDao;
import com.qlnv.model.Role;
import com.qlnv.model.User;

public class UserService {

	private UserDao ud = new UserDao();

	public boolean deleteUserById(int id) {
		return ud.deleteUserById(id);
	}

	public List<User> addNameRole(List<Role> listRole, List<User> listUser) {
		for (User u : listUser) {
			for (Role r : listRole) {
				if (r.getId() == u.getRole().getId()) {
					u.getRole().setName(r.getName());
				}
			}
		}
		return listUser;
	}

	public List<User> getListUser() {
		return ud.getListUser();
	}

	public boolean addUser(String name, String email, String password, String address, String phone, int roleId) {
		return ud.addUser(name, email, password, address, phone, roleId);
	}
}
