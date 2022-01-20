package com.gmail.fomenkoc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.gmail.fomenkoc.dao.impl.RoleDao;
import com.gmail.fomenkoc.dao.impl.UserDao;
import com.gmail.fomenkoc.domain.User;
import com.gmail.fomenkoc.dto.UserLogin;
import com.google.gson.Gson;

@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(LoginServlet.class);

	private String email;
	private String password;
	private User user;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		UserDao userDao = new UserDao();
		
		this.email = req.getParameter("email");
		this.password = req.getParameter("password");
		this.user = userDao.login(email, password);

		if (this.user != null
				&& this.password.equals(this.user.getPassword())) {

			HttpSession session = req.getSession(true);
			session.setAttribute("userID", user.getUserID());
			session.setAttribute("roleID", user.getRoleID());
			

			UserLogin userLogin = new UserLogin();
			userLogin.destinationUrl = "cabinet.jsp";
			userLogin.userEmail = user.getEmail();
			String json = new Gson().toJson(userLogin);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
		} else {

			LOG.warn("Incorrect Email OR Password");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}

}
