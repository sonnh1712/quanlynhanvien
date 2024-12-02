package com.qlnv.service;

import com.qlnv.dao.ProjectDao;

public class ProjectService {

	private ProjectDao pd = new ProjectDao();

	public boolean addProject(String name, String startDate, String endDate, int userId) {
		return pd.addProject(name, startDate, endDate, userId);
	}
}
