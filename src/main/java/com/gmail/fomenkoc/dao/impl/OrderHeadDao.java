package com.gmail.fomenkoc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.gmail.fomenkoc.dao.OrderHeadDaoInterface;
import com.gmail.fomenkoc.domain.OrderHead;
import com.gmail.fomenkoc.utils.DBConnetcion;
import com.gmail.fomenkoc.utils.Mapper;

public class OrderHeadDao implements OrderHeadDaoInterface {

	private static final String READ_ALL = "SELECT * "
										 + "FROM order_head";
	
	private static final String CREATE = 
				"INSERT INTO order_head(order_date, order_status, order_note, "
										+ "user_id, total_quantity, total_sum) "
			  + "VALUES(?, ?, ?, ?, ?, ?)";
	
	private static final String READ = "SELECT * "
									 + "FROM order_head "
									 + "WHERE order_id = ?";
	
	private static final String UPDATE = 
					  "UPDATE order_head "
		  			+ "SET order_date = ?, order_status = ?, order_note = ?, "
		  				+ "user_id = ?, total_quantity = ?, total_sum = ? "
		  			+ "WHERE order_id = ?";
	
	private static final String DELETE = "DELETE "
			 						   + "FROM order_head "
			 						   + "WHERE order_id = ?";
	private static final Logger LOG = Logger.getLogger(OrderDetailsDao.class);
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	
	
	public OrderHeadDao() {
		super();
		this.connection = DBConnetcion.getConnection();
	}

	private void initStatements() {
		this.ps = null;
		this.rs = null;
	}

	@Override
	public OrderHead create(OrderHead orderHead) {
		initStatements();

		try {
			this.ps = this.connection.prepareStatement(CREATE,
					Statement.RETURN_GENERATED_KEYS);
			this.ps.setTimestamp(1, orderHead.getOrderDate());
			this.ps.setString(2, orderHead.getOrderStatus());
			this.ps.setString(3, orderHead.getOrderNote());
			this.ps.setInt(4, orderHead.getUserID());
			this.ps.setDouble(5, orderHead.getTotalQuantity());
			this.ps.setDouble(6, orderHead.getTotalSum());
			this.ps.executeUpdate();
			this.rs = this.ps.getGeneratedKeys();
			if (this.rs.next())
				orderHead.setOrderID(rs.getInt(1));
		} catch (SQLException e) {
			LOG.error(e);
		}

		return orderHead;
	}

	@Override
	public OrderHead read(Integer id) {
		initStatements();
		OrderHead orderHead = null;
		try {
			this.ps = this.connection.prepareStatement(READ);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			if (this.rs.next())
				orderHead = Mapper.orderHead(rs);
		} catch (SQLException e) {
			LOG.error(e);
		}
		return orderHead;
	}

	@Override
	public OrderHead update(OrderHead orderHead) {
		initStatements();

		try {
			this.ps = this.connection.prepareStatement(UPDATE);
			this.ps.setTimestamp(1, orderHead.getOrderDate());
			this.ps.setString(2, orderHead.getOrderStatus());
			this.ps.setString(3, orderHead.getOrderNote());
			this.ps.setInt(4, orderHead.getUserID());
			this.ps.setDouble(5, orderHead.getTotalQuantity());
			this.ps.setDouble(6, orderHead.getTotalSum());
			this.ps.setInt(7, orderHead.getOrderID());
			this.ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		}
		return orderHead;
	}

	@Override
	public void delete(Integer id) {
		initStatements();
		try {
			this.ps = this.connection.prepareStatement(DELETE);
			this.ps.setInt(1, id);
			this.ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		}

	}

	@Override
	public ArrayList<OrderHead> readAll() {
		initStatements();
		ArrayList<OrderHead> heads = new ArrayList<>();
		try {
			this.ps = this.connection.prepareStatement(READ_ALL);
			this.rs = this.ps.executeQuery();
			while (this.rs.next()) {
				heads.add(Mapper.orderHead(rs));
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return heads;
	}


}
