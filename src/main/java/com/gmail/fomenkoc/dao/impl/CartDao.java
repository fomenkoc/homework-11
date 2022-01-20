package com.gmail.fomenkoc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.gmail.fomenkoc.dao.CartDaoInterface;
import com.gmail.fomenkoc.domain.Cart;
import com.gmail.fomenkoc.utils.DBConnetcion;
import com.gmail.fomenkoc.utils.Mapper;

public class CartDao implements CartDaoInterface {

	private static final String READ_ALL = "SELECT * "
										 + "FROM cart";
	
	private static final String CREATE = 
					"INSERT INTO cart(user_id, prod_id, price, quantity, sum) "
				  + "VALUES(?, ?, ?, ?, ?)";
	
	private static final String READ = "SELECT * "
									 + "FROM cart "
									 + "WHERE cart_id = ?";
	
	private static final String READ_BY_USERID =  "SELECT * "
			 									+ "FROM cart "
			 									+ "WHERE user_id = ?";
	
	private static final String UPDATE = 
			   "UPDATE cart "
			 + "SET user_id = ?, prod_id = ?, price = ?, quantity = ?, sum = ? "
			 + "WHERE cart_id = ?";
	
	private static final String DELETE = "DELETE "
									   + "FROM cart "
									   + "WHERE cart_id = ?";
	private static final Logger LOG = Logger.getLogger(CartDao.class);
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public CartDao() {
		super();
		this.connection = DBConnetcion.getConnection();
	}

	private void initStatements() {
		this.ps = null;
		this.rs = null;
	}

	@Override
	public Cart create(Cart cart) {
		initStatements();

		try {
			this.ps = connection.prepareStatement(CREATE,
					Statement.RETURN_GENERATED_KEYS);
			this.ps.setInt(1, cart.getUserID());
			this.ps.setInt(2, cart.getProdID());
			this.ps.setDouble(3, cart.getPrice());
			this.ps.setDouble(4, cart.getQuantity());
			this.ps.setDouble(5, cart.getSum());
			this.ps.executeUpdate();
			this.rs = ps.getGeneratedKeys();
			if (this.rs.next())
				cart.setCartID(rs.getInt(1));
		} catch (SQLException e) {
			LOG.error(e);
		}

		return cart;
	}

	@Override
	public Cart read(Integer id) {
		initStatements();
		Cart cart = null;
		try {
			this.ps = connection.prepareStatement(READ);
			this.ps.setInt(1, id);
			this.rs = ps.executeQuery();
			if (this.rs.next())
				cart = Mapper.cart(rs);
		} catch (SQLException e) {
			LOG.error(e);
		}
		return cart;
	}

	@Override
	public Cart update(Cart cart) {
		initStatements();

		try {
			this.ps = connection.prepareStatement(UPDATE);
			this.ps.setInt(1, cart.getUserID());
			this.ps.setInt(2, cart.getProdID());
			this.ps.setDouble(3, cart.getPrice());
			this.ps.setDouble(4, cart.getQuantity());
			this.ps.setDouble(5, cart.getSum());
			this.ps.setInt(6, cart.getCartID());
			this.ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		}
		return cart;
	}

	@Override
	public void delete(Integer id) {
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
	public ArrayList<Cart> readAll() {
		initStatements();
		ArrayList<Cart> carts = new ArrayList<>();
		try {
			this.ps = connection.prepareStatement(READ_ALL);
			this.rs = this.ps.executeQuery();
			while (this.rs.next()) {
				carts.add(Mapper.cart(rs));
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return carts;
	}

	@Override
	public ArrayList<Cart> readByUserID(Integer userID) {
		initStatements();
		ArrayList<Cart> carts = new ArrayList<>();
		try {
			this.ps = connection.prepareStatement(READ_BY_USERID);
			this.ps.setInt(1, userID);
			this.rs = this.ps.executeQuery();
			while (this.rs.next()) {
				carts.add(Mapper.cart(rs));
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return carts;
	}

}
