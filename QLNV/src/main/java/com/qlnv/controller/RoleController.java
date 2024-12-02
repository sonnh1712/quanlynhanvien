package com.qlnv.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlnv.model.Role;
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
			int editId = Integer.parseInt(req.getParameter("id"));

			req.setAttribute("id", editId);
			req.setAttribute("name", rs.findRoleById(editId).getName());
			req.setAttribute("des", rs.findRoleById(editId).getDes());
			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
			break;
		case "/role-delete":
			int deleteId = Integer.parseInt(req.getParameter("id"));

			String message = rs.deleteRoleById(deleteId) ? null : "Xóa thất bại";

			req.setAttribute("message", message);
			req.setAttribute("id", deleteId);
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

			String message = rs.addRole(addName, addDes) ? "Thêm thành công" : "Thêm thất bại";

			req.setAttribute("message", message);
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		case "/role-edit":
			int editId = Integer.parseInt(req.getParameter("id"));
			String editName = req.getParameter("name");
			String editDes = req.getParameter("des");

			String message1 = rs.editRole(editId, editName, editDes) ? "Cập nhật thành công" : "Cập nhật thất bại";
			req.setAttribute("message", message1);

			Role role = rs.findRoleById(editId);
			req.setAttribute("id", editId);
			req.setAttribute("name", role.getName());
			req.setAttribute("des", role.getDes());

			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
}
