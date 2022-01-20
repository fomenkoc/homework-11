package com.gmail.fomenkoc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gmail.fomenkoc.dao.UserDaoInterface;
import com.gmail.fomenkoc.domain.Cart;
import com.gmail.fomenkoc.domain.User;
import com.gmail.fomenkoc.utils.DBConnetcion;
import com.gmail.fomenkoc.utils.Mapper;

public class UserDao implements UserDaoInterface {

	private static final String READ_ALL = "SELECT * "
										 + "FROM user";
	
	private static final String CREATE = 
			"INSERT INTO user(email, first_name, last_name, role_id, password) "
		  + "VALUES(?, ?, ?, ?, ?)";
	
	private static final String READ = "SELECT * "
			 						 + "FROM user "
			 						 + "WHERE user_id = ?";
	
	private static final String UPDATE = 
	  "UPDATE user "
	+ "SET email = ?, first_name = ?, last_name = ?, role_id = ?, password = ? "
	+ "WHERE user_id = ?";
	
	private static final String DELETE = "DELETE "
			 						   + "FROM user "
			 						   + "WHERE user_id = ?";
	
	private static final String LOGIN = "SELECT * "
									  + "FROM user "
									  + "WHERE email = ? AND password = ?";
	public static final Logger LOG = Logger.getLogger(UserDao.class);
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public UserDao() {
		super();
		this.connection = DBConnetcion.getConnection();
		
	}

	private void initStatements() {
		this.ps = null;
		this.rs = null;
	}

	@Override
	public User create(User user) {
		initStatements();
		try {
			this.ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			this.ps.setString(1, user.getEmail());
			this.ps.setString(2, user.getFirstName());
			this.ps.setString(3, user.getLastName());
			this.ps.setInt(4, user.getRoleID());
			this.ps.setString(5, user.getPassword());
			this.ps.executeUpdate();
			this.rs = this.ps.getGeneratedKeys();
			
			if (this.rs.next())
				user.setUserID(rs.getInt(1));
		} catch (SQLException e) {
			LOG.error(e);
		}
		return user;
	}

	@Override
	public User read(Integer id) {
		initStatements();
		User user = null;
		try {
			this.ps = connection.prepareStatement(READ);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			if (this.rs.next())
				user = Mapper.user(rs);
		} catch (SQLException e) {
			LOG.error(e);
		}
		return user;
	}

	@Override
	public User update(User user) {
		initStatements();
		try {
			this.ps = connection.prepareStatement(UPDATE);
			this.ps.setString(1, user.getEmail());
			this.ps.setString(2, user.getFirstName());
			this.ps.setString(3, user.getLastName());
			this.ps.setInt(4, user.getRoleID());
			this.ps.setString(5, user.getPassword());
			this.ps.setInt(6, user.getUserID());
			this.ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		}
		return user;
	}

	@Override
	public void delete(Integer id) {
		CartDao cartDao = new CartDao();
		List<Cart> carts = cartDao.readByUserID(id);
		for (Cart cart : carts) {
			cartDao.delete(cart.getCartID());
		}
		
		initStatements();
		try {
			this.ps = connection.prepareStatement(DELETE);
			this.ps.setInt(1, id);
			this.ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		}

	}

	@Override
	public ArrayList<User> readAll() {
		initStatements();
		ArrayList<User> users = new ArrayList<>();
		try {
			this.ps = connection.prepareStatement(READ_ALL);
			this.rs = this.ps.executeQuery();
			while (this.rs.next()) {
				users.add(Mapper.user(rs));
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return users;
	}

	@Override
	public User login(String email, String password) {
		User user = null;
		try {
			this.ps = connection.prepareStatement(LOGIN);
			this.ps.setString(1, email);
			this.ps.setString(2, password);
			this.rs = this.ps.executeQuery();

			if (this.rs.next())
				user = Mapper.user(rs);
		} catch (SQLException e) {
			LOG.error(e);
		}
		return user;
	}

	

}
