package com.gmail.fomenkoc.dao;

import java.util.ArrayList;

import com.gmail.fomenkoc.domain.Cart;
import com.gmail.fomenkoc.shared.CRUDInterface;

public interface CartDaoInterface extends CRUDInterface<Cart> {
	
	public ArrayList<Cart> readByUserID(Integer userID);

}
