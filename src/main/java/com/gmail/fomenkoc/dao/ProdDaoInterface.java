package com.gmail.fomenkoc.dao;

import java.util.Map;

import com.gmail.fomenkoc.domain.Prod;
import com.gmail.fomenkoc.shared.CRUDInterface;

public interface ProdDaoInterface extends CRUDInterface<Prod> {
	
	public Map<Integer, Prod> readAllMap();

}
