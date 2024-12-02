package com.qlnv.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlnv.model.User;
import com.qlnv.service.ProjectService;

@WebServlet(urlPatterns = { "/project-add" })
public class ProjectController extends HttpServlet {

	private ProjectService ps = new ProjectService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/project-add":
			req.getRequestDispatcher("project-add.jsp").forward(req, resp);
			break;

		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/project-add":
			String addName = req.getParameter("name");
			String addStartDate = req.getParameter("start-date");
			String addEndDate = req.getParameter("end-date");
			int addUserId = ((User) req.getSession().getAttribute("user")).getId();

			String message = ps.addProject(addName, addStartDate, addEndDate, addUserId) ? "Thêm thành công"
					: "Thêm thất bại";

			req.setAttribute("message", message);
			req.getRequestDispatcher("project-add.jsp").forward(req, resp);
			break;

		default:
			break;
		}
	}
}
