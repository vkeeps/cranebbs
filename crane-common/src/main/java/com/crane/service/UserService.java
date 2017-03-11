
package com.crane.service;

import com.crane.exception.BusinessException;
import com.crane.po.model.User;
import com.crane.po.query.UserQuery;
import com.crane.po.vo.PaginationResult;

/**
 * ClassName:UserService <br/>
 * Date:     2016年9月19日 下午4:50:03 <br/>
 * @author   多多洛
 * Copyright (c) 2016, ulewo.com All Rights Reserved. 
 */
public interface UserService {
	/**
	 * 
	 * restister:(注册)
	 * @author luohaili
	 * @param user
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	public void restister(User user) throws BusinessException;

	/**
	 * 
	 * findUserByUserName:(根据用户名查询用户)
	 * @author luohaili
	 * @param userName
	 * @return
	 * @since JDK 1.7
	 */
	public User findUserByUserName(String userName);

	/**
	 * 
	 * findUserByEmail:(根据邮箱查询用户)
	 * @author luohaili
	 * @param email
	 * @return
	 * @since JDK 1.7
	 */
	public User findUserByEmail(String email);

	/**
	 * 
	 * findUserByUserID:(通过userId获取用户信息). <br/>
	 *
	 * @author 多多洛
	 * @param userId
	 * @return
	 * @since JDK 1.7
	 */
	public User findUserByUserId(String userId);

	/**
	 * 
	 * login:(登录)
	 * @author luohaili
	 * @param account
	 * @param password
	 * @param encodePwd TODO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	public User login(String account, String password) throws BusinessException;

	/**
	 * 
	 * sendCheckCode:(发送验证码)
	 * @author luohaili
	 * @param email
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	public void sendCheckCode(String email) throws BusinessException;

	/**
	 * 
	 * resetPwd:(重置密码)
	 * @author luohaili
	 * @param email
	 * @param password
	 * @param checkCode
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	public void resetPwd(String email, String password, String checkCode) throws BusinessException;

	/**
	 * addMark:(加积分)
	 * @author luohaili
	 * @param userId
	 * @param mark
	 * @return TODO
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	public int changeMark(Integer userId, Integer mark) throws BusinessException;

	/**
	 * 
	 * update:(更新用户信息). <br/>
	 *
	 * @author 多多洛
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	public void updateInfo(User user) throws BusinessException;

	/**
	 * 更新最后登录时间
	 * updateLastLoginTime:(这里用一句话描述这个方法的作用)
	 * @author luohaili
	 * @param user
	 * @since JDK 1.7
	 */
	public void updateLastLoginTime(Integer userId);

	/**
	 * 
	 * changePwd:(修改密码)
	 * @author luohaili
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @since JDK 1.7
	 */
	public void changePwd(Integer userId, String oldPwd, String newPwd) throws BusinessException;

	/**
	 * 
	 * copyUserIcon:(复制用户头像)
	 * @author luohaili
	 * @param sourceIcon
	 * @param targetIcon
	 * @since JDK 1.7
	 */
	public void copyUserIcon(String sourceIcon, String targetIcon);

	/**
	 * 
	 * findUserInfo4UserHome:(查询个人主页信息)
	 *
	 * @author 多多洛
	 * @param userId
	 * @param sessionUserId TODO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	/*public User findUserInfo4UserHome(Integer userId, Integer sessionUserId) throws BusinessException;*/

	/**
	 * 
	 * findUserByPage:(分页查询用户)
	 * @author luohaili
	 * @param query
	 * @return
	 * @since JDK 1.7
	 */
	public PaginationResult<User> findUserByPage(UserQuery query);

	/**
	 * 
	 * rewardMark:(奖励积分)
	 * @author luohaili
	 * @param userId
	 * @param mark
	 * @since JDK 1.7
	 */
	public void rewardMark(Integer userId, Integer mark, String message);

	/**
	 * 
	 * warnUser:(发送警告信息)
	 * @author luohaili
	 * @param userId
	 * @param message
	 * @since JDK 1.7
	 */
	public void warnUser(Integer userId, String message);

	/**
	 * 删除用户
	 * delete:(这里用一句话描述这个方法的作用)
	 * @author luohaili
	 * @param userId
	 * @since JDK 1.7
	 */
	public void delete(Integer userId);
}
