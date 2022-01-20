package com.gmail.fomenkoc.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.gmail.fomenkoc.domain.Cart;
import com.gmail.fomenkoc.domain.OrderDetails;
import com.gmail.fomenkoc.domain.OrderHead;
import com.gmail.fomenkoc.domain.Prod;
import com.gmail.fomenkoc.domain.Role;
import com.gmail.fomenkoc.domain.User;

public class Mapper {

	public static Cart cart(ResultSet rs) {

		Cart cart = null;

		try {
			Integer cartID = rs.getInt("cart_id");
			Integer userID = rs.getInt("user_id");
			Integer prodID = rs.getInt("prod_id");
			Double price = rs.getDouble("price");
			Double quantity = rs.getDouble("quantity");
			Double sum = rs.getDouble("sum");
			cart = new Cart(cartID, userID, prodID, price, quantity, sum);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cart;
	}

	public static OrderDetails orderDetails(ResultSet rs) {
		OrderDetails orderDetails = null;
		try {
			Integer orderDetID = rs.getInt("order_det_id");
			Integer orderID = rs.getInt("order_id");
			Integer prodID = rs.getInt("prod_id");
			Double quantity = rs.getDouble("quantity");
			Double price = rs.getDouble("price");
			Double sum = rs.getDouble("sum");
			orderDetails = new OrderDetails(orderDetID, orderID, prodID,
					quantity, price, sum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetails;
	}

	public static OrderHead orderHead(ResultSet rs) {
		OrderHead orderHead = null;
		try {
			Integer orderID = rs.getInt("order_id");
			Timestamp orderDate = rs.getTimestamp("order_date");
			String orderStatus = rs.getString("order_status");
			String orderNote = rs.getString("order_note");
			Integer userID = rs.getInt("user_id");
			Double totalQuantity = rs.getDouble("total_quantity");
			Double totalSum = rs.getDouble("total_sum");
			orderHead = new OrderHead(orderID, orderDate, orderStatus,
					orderNote, userID, totalQuantity, totalSum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderHead;
	}

	public static Prod prod(ResultSet rs) {

		Prod prod = null;

		try {
			Integer prodID = rs.getInt("prod_id");
			String prodName = rs.getString("prod_name");
			String description = rs.getString("description");
			Double price = rs.getDouble("price");
			prod = new Prod(prodID, prodName, description, price);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prod;
	}

	public static Role role(ResultSet rs) {
		Role role = null;
		Integer roleID;
		try {
			roleID = rs.getInt("role_id");
			String roleName = rs.getString("role_name");
			Boolean isStaff = rs.getBoolean("is_staff");
			role = new Role(roleID, roleName, isStaff);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	public static User user(ResultSet rs) {
		User user = null;
		try {
			Integer userID = rs.getInt("user_id");
			String email = rs.getString("email");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			Integer roleID = rs.getInt("role_id");
			String password = rs.getString("password");
			user = new User(userID, email, firstName, lastName, roleID,
					password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

}
