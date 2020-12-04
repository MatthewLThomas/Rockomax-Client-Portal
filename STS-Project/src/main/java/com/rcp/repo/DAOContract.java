package com.rcp.repo;

import java.util.List;

public interface DAOContract<T,I> {
	int create(T t);
	List<T> findAll();
	List<T> findBy (I i);
	int update(T t);
	int delete(T t);
	int deleteBy(I i);
	
	
}
