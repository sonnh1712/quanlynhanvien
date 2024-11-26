package com.qlnv.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlnv.service.RoleService;

@WebServlet(urlPatterns = { "/user-add" })
public class UserController extends HttpServlet {

	private RoleService rs = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/user-add":
			req.setAttribute("listRole", rs.getListRole());

			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;

		default:
			break;
		}
	}
}
