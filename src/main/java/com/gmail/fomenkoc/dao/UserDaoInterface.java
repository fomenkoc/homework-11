package com.gmail.fomenkoc.dao;

import com.gmail.fomenkoc.domain.User;
import com.gmail.fomenkoc.shared.CRUDInterface;

public interface UserDaoInterface extends CRUDInterface<User>{
	
	public User login (String email, String password);
	
	

}
