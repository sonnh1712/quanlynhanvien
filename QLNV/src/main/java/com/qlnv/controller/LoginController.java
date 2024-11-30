package com.qlnv.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qlnv.model.User;
import com.qlnv.service.UserService;
import com.qlnv.util.PasswordUtil;

@WebServlet(urlPatterns = { "/login", "/logout" })
public class LoginController extends HttpServlet {

	private UserService us = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/login":
			if (Boolean.TRUE.equals(req.getSession().getAttribute("login"))) {
				resp.sendRedirect(req.getContextPath() + "/home");
			} else {
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
			break;
		case "/logout":
			HttpSession session = req.getSession(false);

			if (session != null) {
				session.invalidate();
			}

			resp.sendRedirect(req.getContextPath() + "/login");
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case "/login":
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			User user = us.findUserByEmail(email);
			if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
				req.getSession().setAttribute("login", true);
				req.getSession().setAttribute("user", user);
				resp.sendRedirect(req.getContextPath() + "/home");
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
			break;

		default:
			break;
		}
	}
}
