package com.crane.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper<T,Q> extends BaseMapper<T, Q>{
	/**
	 * 
	 * changeUserMakr:(改变积分)
	 * @author luohaili
	 * @param userId
	 * @param mark
	 * @return
	 * @since JDK 1.7
	 */
	/*public int changeUserMark(@Param("userId") Integer userId, @Param("changeMark") Integer changeMark);*/
   
}