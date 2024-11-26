package com.qlnv.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlnv.service.RoleService;

@WebServlet(urlPatterns = { "/role-add", "/role-table", "/role-edit", "/role-delete" })
public class RoleController extends HttpServlet {

	private RoleService rs = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/role-add":
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		case "/role-table":
			req.setAttribute("listRole", rs.getListRole());

			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		case "/role-edit":
			int editId = Integer.valueOf(req.getParameter("id"));

			req.setAttribute("id", editId);
			req.setAttribute("name", rs.findRoleById(editId).getName());
			req.setAttribute("des", rs.findRoleById(editId).getDes());

			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
			break;
		case "/role-delete":
			int deleteId = Integer.valueOf(req.getParameter("id"));

			if (!rs.deleteRoleById(deleteId)) {
				req.setAttribute("message", "xoa that bai");
				req.setAttribute("id", deleteId);
			}
			req.setAttribute("listRole", rs.getListRole());

			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
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
		case "/role-add":
			String addName = req.getParameter("name");
			String addDes = req.getParameter("des");

			if (rs.addRole(addName, addDes)) {
				req.setAttribute("message", "them thanh cong");
			} else {
				req.setAttribute("message", "them that bai");
			}

			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		case "/role-edit":
			int editId = Integer.valueOf(req.getParameter("id"));
			String editName = req.getParameter("name");
			String editDes = req.getParameter("des");

			if (rs.editRole(editId, editName, editDes)) {
				req.setAttribute("message", "cap nhat thanh cong");
			} else {
				req.setAttribute("message", "cap nhat that bai");
			}
			req.setAttribute("id", editId);
			req.setAttribute("name", rs.findRoleById(editId).getName());
			req.setAttribute("des", rs.findRoleById(editId).getDes());

			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
}
