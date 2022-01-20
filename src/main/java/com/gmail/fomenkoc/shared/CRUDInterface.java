package com.gmail.fomenkoc.shared;

import java.util.ArrayList;

public interface CRUDInterface<T> {

	T create(T t);

	T read(Integer id);

	T update(T t);

	void delete(Integer id);
	
	ArrayList<T> readAll();
}
