package com.gmail.fomenkoc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.gmail.fomenkoc.dao.RoleDaoInterface;
import com.gmail.fomenkoc.domain.Role;
import com.gmail.fomenkoc.utils.DBConnetcion;
import com.gmail.fomenkoc.utils.Mapper;

public class RoleDao implements RoleDaoInterface {

	private static final String READ_ALL = "SELECT * "
										 + "FROM role";
	
	private static final String READ_STAFF =  "SELECT * "
											+ "FROM role "
											+ "WHERE is_staff = true";
	
	private static final String CREATE = "INSERT INTO role(role_name) "
									   + "VALUES(?)";
	
	private static final String READ = "SELECT * "
			 						 + "FROM role "
			 						 + "WHERE role_id = ?";
	
	private static final String UPDATE = "UPDATE role "
									   + "SET role_name = ? "
									   + "WHERE role_id = ?";
	
	private static final String DELETE = "DELETE "
			 						   + "FROM role "
			 						   + "WHERE role_id = ?";
	private static final Logger LOG = Logger.getLogger(RoleDao.class);
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public RoleDao() {
		super();
		this.connection = DBConnetcion.getConnection();
	}

	private void initStatements() {
		this.ps = null;
		this.rs = null;
	}

	@Override
	public Role create(Role role) {
		initStatements();
		try {
			this.ps = this.connection.prepareStatement(CREATE,
					Statement.RETURN_GENERATED_KEYS);
			this.ps.setString(1, role.getRoleName());
			this.ps.executeUpdate();
			this.rs = this.ps.getGeneratedKeys();
			if (this.rs.next())
				role.setRoleID(this.rs.getInt(1));
		} catch (SQLException e) {
			LOG.error(e);
		}
		return role;
	}

	@Override
	public Role read(Integer id) {
		initStatements();
		Role role = null;
		try {
			this.ps = connection.prepareStatement(READ);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			if (this.rs.next())
				role = Mapper.role(rs);
		} catch (SQLException e) {
			LOG.error(e);
		}
		return role;
	}

	@Override
	public Role update(Role role) {
		initStatements();
		try {
			this.ps = connection.prepareStatement(UPDATE);
			this.ps.setString(1, role.getRoleName());
			this.ps.setInt(2, role.getRoleID());
			this.ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		}

		return role;
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
	public ArrayList<Role> readAll() {
		initStatements();
		ArrayList<Role> roles = new ArrayList<>();
		try {
			this.ps = connection.prepareStatement(READ_ALL);
			this.rs = this.ps.executeQuery();
			while (this.rs.next()) {
				roles.add(Mapper.role(rs));
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return roles;
	}

	@Override
	public ArrayList<Role> readStaff() {
		initStatements();
		ArrayList<Role> roles = new ArrayList<>();
		try {
			this.ps = connection.prepareStatement(READ_STAFF);
			this.rs = this.ps.executeQuery();
			while (this.rs.next()) {
				roles.add(Mapper.role(rs));
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return roles;
	}

	@Override
	public Map<Integer, Role> readAllMap() {
		return readAll().stream().collect(
				Collectors.toMap(Role::getRoleID, Function.identity()));
	}

}
