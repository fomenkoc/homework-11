package com.gmail.fomenkoc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.fomenkoc.dao.impl.UserDao;
import com.gmail.fomenkoc.domain.User;

@WebServlet(name = "registration", urlPatterns = { "/registration" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		String firstName = req.getParameter("firstName");
		String lastNmae = req.getParameter("lastName");
		Integer roleID = 2;
		String password = req.getParameter("password");

		User user = new User(email, firstName, lastNmae, roleID, password);
		UserDao userDao = new UserDao();
		user = userDao.create(user);

		if (user.getUserID() != 0) {
			/*
			 * req.setAttribute("firstName", user.getFirstName());
			 * req.setAttribute("lastName", user.getLastNmae());
			 * req.setAttribute("email", user.getEmail());
			 * req.getRequestDispatcher("welcome.jsp").forward(req, resp);
			 */
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("Success");
		}

	}

}
