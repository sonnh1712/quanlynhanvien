package com.qlnv.service;

import java.util.List;

import com.qlnv.dao.RoleDao;
import com.qlnv.model.Role;

public class RoleService {

	private RoleDao rd = new RoleDao();

	public boolean deleteRoleById(int id) {
		return rd.deleteRoleById(id);
	}

	public boolean editRole(int id, String name, String des) {
		return rd.editRole(id, name, des);
	}

	public Role findRoleById(int id) {
		return rd.findRoleById(id);
	}

	public List<Role> getListRole() {
		return rd.getListRole();
	}

	public boolean addRole(String name, String des) {
		return rd.addRole(name, des);
	}
}
