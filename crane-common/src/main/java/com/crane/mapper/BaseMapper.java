package com.crane.mapper;

import java.util.List;

public interface BaseMapper<T,Q> {
	
	public int insert(T t);

	public List<T> selectList(Q q);

	public Integer selectCount(Q q);

	public int update(T t);

	public int delete(Q q);

}
