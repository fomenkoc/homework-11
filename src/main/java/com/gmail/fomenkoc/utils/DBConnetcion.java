package com.gmail.fomenkoc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class DBConnetcion {
	private static String USER_NAME;
	private static String PASSWORD;
	private static final String URL = 
						"jdbc:mysql://localhost:3306/Store?serverTimezone=UTC";
	private static final Logger LOG = Logger.getLogger(DBConnetcion.class);

	public static Connection getConnection(String userName, String password)
			throws SQLException, InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		USER_NAME = userName;
		PASSWORD = password;
		Class.forName("com.mysql.cj.jdbc.Driver");
		DOMConfigurator.configure("logConfig.xml");

		return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
	}
	
	public static Connection getConnection() {
		
			try {
				return getConnection("user4lessons", "user4lessons");
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				
				LOG.error(e);
				return null;
			}
		
	}
}
