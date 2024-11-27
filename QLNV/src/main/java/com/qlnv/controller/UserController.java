package com.qlnv.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlnv.service.RoleService;
import com.qlnv.service.UserService;

@WebServlet(urlPatterns = { "/user-add", "/user-table", "/user-delete", "/user-edit" })
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

			if (!us.deleteUserById(deleteId)) {
				req.setAttribute("id", deleteId);
				req.setAttribute("message", "Xoa that bai");
			}
			req.setAttribute("listUser", us.addNameRole(rs.getListRole(), us.getListUser()));

			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
		case "/user-edit":
			int editId = Integer.valueOf(req.getParameter("id"));

			req.setAttribute("id", editId);
			req.setAttribute("listRole", rs.getListRole());

			req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
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
		case "/user-edit":
			int editId = Integer.valueOf(req.getParameter("id"));
			String editName = req.getParameter("name");
			String editEmail = req.getParameter("email");
			String editPassword = req.getParameter("password");
			String editAddress = req.getParameter("address");
			String editPhone = req.getParameter("phone");
			int editRoleId = Integer.valueOf(req.getParameter("role-id"));

			if (us.editUser(editId, editName, editEmail, editPassword, editAddress, editPhone, editRoleId)) {
				req.setAttribute("message", "cap nhat thanh cong");
			} else {
				req.setAttribute("message", "cap nhat that bai");
			}
			req.setAttribute("id", editId);
			req.setAttribute("listRole", rs.getListRole());

			req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
}
