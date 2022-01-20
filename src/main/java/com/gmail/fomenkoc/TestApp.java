package com.gmail.fomenkoc;

import com.gmail.fomenkoc.dao.impl.RoleDao;
import com.gmail.fomenkoc.domain.Role;

public class TestApp {

	public static void main(String[] args) {

		RoleDao roleDao = new RoleDao();
		Role role = new Role("Test role");
		
		roleDao.create(role);
		
		role = roleDao.read(1);
		System.out.println(role);
	}
}