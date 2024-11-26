package com.qlnv.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlnv.service.RoleService;
import com.qlnv.service.UserService;

@WebServlet(urlPatterns = { "/user-add", "/user-table", "/user-delete" })
public class UserController extends HttpServlet {

	private RoleService rs = new RoleService();
	private UserService us = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/user-add":
			req.setAttribute("listRole", rs.getListRole());

			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		case "/user-table":
			req.setAttribute("listUser", us.addNameRole(rs.getListRole(), us.getListUser()));

			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
		case "/user-delete":
			int deleteId = Integer.valueOf(req.getParameter("id"));

			if(!us.deleteUserById(deleteId)) {
				req.setAttribute("id", deleteId);
				req.setAttribute("message", "Xoa that bai");
			}
			req.setAttribute("listUser", us.addNameRole(rs.getListRole(), us.getListUser()));

			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();

		switch (path) {
		case "/user-add":
			String addName = req.getParameter("name");
			String addEmail = req.getParameter("email");
			String addPassword = req.getParameter("password");
			String addAddress = req.getParameter("address");
			String addPhone = req.getParameter("phone");
			int addRoleId = Integer.valueOf(req.getParameter("role-id"));

			if (us.addUser(addName, addEmail, addPassword, addAddress, addPhone, addRoleId)) {
				req.setAttribute("message", "them thanh cong");
			} else {
				req.setAttribute("message", "them that bai");
			}
			req.setAttribute("listRole", rs.getListRole());

			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;

		default:
			break;
		}
	}
}
