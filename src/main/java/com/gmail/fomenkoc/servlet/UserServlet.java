package com.gmail.fomenkoc.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.fomenkoc.dao.impl.RoleDao;
import com.gmail.fomenkoc.dao.impl.UserDao;
import com.gmail.fomenkoc.domain.Role;
import com.gmail.fomenkoc.domain.User;
import com.gmail.fomenkoc.dto.UserDto;
import com.google.gson.Gson;

@WebServlet(name = "user", urlPatterns = { "/user" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		User user;
		Integer userID;
		HttpSession session = request.getSession();
		userID = Integer.parseInt(session.getAttribute("userID").toString());
		user = userDao.read(userID);
		
		String json = new Gson().toJson(user);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		

		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		RoleDao roleDao = new RoleDao();
		List<User> users = userDao.readAll();
		Map<Integer, Role> idToRole = roleDao.readAllMap();
		List<UserDto> userDtos = map(users, idToRole);

		String json = new Gson().toJson(userDtos);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer userID = Integer.parseInt(request.getParameter("userID"));
		userDao.delete(userID);
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
		
	}
	
	private List<UserDto> map(List<User> users, Map<Integer, Role> idToRole){
		return users.stream().map(user -> {
			UserDto userDto = new UserDto();
			Role role = idToRole.get(user.getRoleID());
			userDto.userID = user.getUserID();
			userDto.email = user.getEmail();
			userDto.firstName = user.getFirstName();
			userDto.lastName = user.getLastName();
			userDto.roleID = user.getRoleID();
			userDto.roleName = role.getRoleName();
			return userDto;
			
		}).collect(Collectors.toList());
	}
	

}
