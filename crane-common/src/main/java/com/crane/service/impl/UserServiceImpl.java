/**
 * Project Name:ulewo-web
 * File Name:UserServiceImpl.java
 * Package Name:com.ulewo.service.impl
 * Date:2015年9月19日下午4:50:35
 * Copyright (c) 2015, ulewo.com All Rights Reserved.
 *
*/

package com.crane.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crane.cache.BlackUserCache;
import com.crane.exception.BusinessException;
import com.crane.mapper.UserMapper;
import com.crane.po.config.ConfigInfo;
import com.crane.po.enums.PageSize;
import com.crane.po.enums.TextLengthEnum;
import com.crane.po.model.User;
import com.crane.po.query.UserQuery;
import com.crane.po.vo.PaginationResult;
import com.crane.po.vo.SimplePage;
import com.crane.service.UserService;
import com.crane.utils.Constants;
import com.crane.utils.FileUtils;
import com.crane.utils.SendMailUtils;
import com.crane.utils.ServerUtils;
import com.crane.utils.StringTools;

/**
 * ClassName:UserServiceImpl <br/>
 * Date:     2015年9月19日 下午4:50:35 <br/>
 * @author   多多洛
 * Copyright (c) 2015, ulewo.com All Rights Reserved. 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper<User, UserQuery> userMapper;
/*
	@Autowired
	private UserFriendService userFriendService;*/

	/*@Autowired
	private MessageService messageService;*/

	@Autowired
	private ConfigInfo configInfo;

	public void restister(User user) throws BusinessException {
		/**
		 * 校验邮箱，用户名，密码是否合法
		 */
		String userName = user.getUserName();
		String email = user.getEmail();
		String password = user.getPassword();
		if (StringTools.isEmpty(userName) || StringTools.isEmpty(email) || StringTools.isEmpty(password)
				|| userName.length() > Constants.LENGTH_20 || password.length() > Constants.LENGTH_16
				|| password.length() < Constants.LENGTH_6 || !StringTools.checkEmail(email)
				|| !StringTools.checkUserName(userName) || !StringTools.checkPassWord(password)) {
			throw new BusinessException("输入参数不合法");
		}
		/**
		 * 校验用户是否已经存在
		 */
		if (this.findUserByUserName(user.getUserName()) != null) {
			throw new BusinessException("用户已经存在");
		}
		/**
		 * 
		 * 校验邮箱是否已经存在
		 */
		if (this.findUserByEmail(user.getEmail()) != null) {
			throw new BusinessException("邮箱已经存在");
		}
		user.setPassword(StringTools.encodeByMD5(password));

		user.setUserBg(Constants.USER_IMG_PATH_USER_BG + ((int) (Math.random() * 10) + 1) + Constants.IMAGE_SUFFIX_JPG);
		Date curDate = new Date();
		user.setRegisterTime(curDate);
		user.setLastLoginTime(curDate);
		this.userMapper.insert(user);

		//复制头像，更新头像信息
		String icon = Constants.USER_IMG_PATH_USER_ICON + ((int) (Math.random() * 10) + 1) + ".png";
		String targetIcon = user.getUserId() + ".jpg";
		copyUserIcon(icon, targetIcon);
		user.setUserIcon(Constants.PATH_AVATARS_SUFFIX + targetIcon);
		this.updateInfo(user);
	}

	public void copyUserIcon(String sourceIcon, String targetIcon) {
		File sourcefile = new File(ServerUtils.getRealPath() + Constants.PATH_DEFAULT_USER_ICON + sourceIcon);
		File targetFile = new File(ServerUtils.getRealPath() + Constants.PATH_AVATARS + targetIcon);
		FileUtils.copyFile(sourcefile, targetFile);
	}

	@Override
	public User login(String account, String password, Boolean encodePwd) throws BusinessException {
		if (StringTools.isEmpty(account) || StringTools.isEmpty(password)) {
			throw new BusinessException("输入参数不合法");
		}
		User user = null;
		//邮箱登陆
		if (account.contains("@")) {
			user = this.findUserByEmail(account);
		} else {
			user = this.findUserByUserName(account);
		}
		if (null == user) {
			throw new BusinessException("用户不存在");
		}
		if (encodePwd) {
			password = StringTools.encodeByMD5(password);
		}
		if (!user.getPassword().equals(password)) {
			throw new BusinessException("密码错误");
		}
		updateLastLoginTime(user.getUserId());
		return user;
	}

	@Override
	public void sendCheckCode(String email) throws BusinessException {
		if (StringTools.isEmpty(email)) {
			throw new BusinessException("请求参数错误");
		}

		User user = this.findUserByEmail(email);
		if (null == user) {
			throw new BusinessException("输入的邮箱不存在");
		}
		String checkCode = createCheckCode();
		String title = "ulewo邮箱找回密码邮件";
		StringBuilder content = new StringBuilder("亲爱的" + email + "<br><br>");
		content.append("欢迎使用ulewo找回密码功能。(http://ulewo.com)<br><br>");
		content.append("您的验证码是：<span style='color:red;'>" + checkCode + "</span>,如果不是本人操作，请忽略此邮件<br><br>");
		content.append("您的注册邮箱是:" + email + "<br><br>");
		content.append("希望你在有乐窝社区的体验有益和愉快！<br><br>");
		content.append("- 有乐窝社区(http://ulewo.com)");
		try {
			SendMailUtils.sendEmail(configInfo.getFindemail(), configInfo.getFindpwd(), title, content.toString(),
					new String[] { email });
		} catch (Exception e) {
			throw new BusinessException("发送邮件失败,请稍后再试");
		}
		//更新数据库
		user.setActivationCode(checkCode);
		this.userMapper.update(user);

	}

	public void resetPwd(String email, String password, String checkCode) throws BusinessException {
		if (StringTools.isEmpty(email) || StringTools.isEmpty(password) || password.length() > Constants.LENGTH_16
				|| password.length() < Constants.LENGTH_6 || !StringTools.checkPassWord(password)) {
			throw new BusinessException("输入参数不合法");
		}
		User user = this.findUserByEmail(email);
		if (null == user) {
			throw new BusinessException("邮箱不存在");
		}
		if (!user.getActivationCode().equals(checkCode)) {
			throw new BusinessException("验证码错误");
		}
		user.setPassword(StringTools.encodeByMD5(password));
		this.userMapper.update(user);
	}

	private String createCheckCode() {
		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
		int codeCount = 6;
		Random random = new Random();
		StringBuilder randomCode = new StringBuilder();
		int codeLength = codeSequence.length;
		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random.nextInt(codeLength)]);
			randomCode.append(strRand);
		}
		return randomCode.toString();
	}

	public User findUserByUserName(String userName) {
		UserQuery query = new UserQuery();
		query.setUserName(userName);
		List<User> userList = userMapper.selectList(query);
		if (userList.size() == 1) {
			return userList.get(0);
		}
		return null;
	}

	public User findUserByEmail(String email) {
		UserQuery query = new UserQuery();
		query.setEmail(email);
		List<User> userList = userMapper.selectList(query);
		if (userList.size() == 1) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public User findUserByUserId(String userId) {
		UserQuery query = new UserQuery();
		query.setUserId(userId);
		List<User> userList = userMapper.selectList(query);
		if (userList.size() == 1) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public int changeMark(Integer userId, Integer mark) throws BusinessException {
		return userMapper.changeUserMark(userId, mark);
	}

	@Override
	public void updateInfo(User user) throws BusinessException {
		if (!StringUtils.isEmpty(user.getAddress())
				&& user.getAddress().length() > TextLengthEnum.LENGTH_50.getLength()
				|| !StringUtils.isEmpty(user.getWork())
				&& user.getWork().length() > TextLengthEnum.LENGTH_50.getLength()
				|| !StringUtils.isEmpty(user.getCharacters())
				&& user.getCharacters().length() > TextLengthEnum.LENGTH_200.getLength()
				|| !StringUtils.isEmpty(user.getBirthday())
				&& user.getBirthday().length() > TextLengthEnum.LENGTH_10.getLength()) {
			throw new BusinessException("参数错误");
		}
		user.setLastLoginTime(null);
		user.setRegisterTime(null);
		user.setPassword(null);
		user.setActivationCode(null);
		userMapper.update(user);
	}

	@Override
	public void updateLastLoginTime(Integer userId) {
		User newUser = new User();
		newUser.setLastLoginTime(new Date());
		newUser.setUserId(userId);
		userMapper.update(newUser);
	}

	@Override
	public void changePwd(Integer userId, String oldPwd, String newPwd) throws BusinessException {

		if (StringTools.isEmpty(oldPwd) || StringTools.isEmpty(newPwd) || newPwd.length() > Constants.LENGTH_16
				|| !StringTools.checkPassWord(newPwd)) {
			throw new BusinessException("参数不合法");
		}
		User user = this.findUserByUserId(userId.toString());
		if (!user.getPassword().equals(StringTools.encodeByMD5(oldPwd))) {
			throw new BusinessException("原始密码不正确");
		}
		user.setPassword(newPwd);
		userMapper.update(user);
	}

	

	@Override
	public PaginationResult<User> findUserByPage(UserQuery query) {
		int count = this.userMapper.selectCount(query);
		int pageSize = PageSize.SIZE20.getSize();
		int pageNo = 0;
		if (null != query.getPageNo()) {
			pageNo = query.getPageNo();
		}
		SimplePage page = new SimplePage(pageNo, count, pageSize);
		query.setPage(page);
		List<User> list = this.userMapper.selectList(query);
		PaginationResult<User> result = new PaginationResult<User>(page, list);
		return result;
	}

	@Override
	public void rewardMark(Integer userId, Integer mark, String message) {
		this.userMapper.changeUserMark(userId, mark);
		//发送消息
		Set<Integer> userList = new HashSet<Integer>();
		userList.add(userId);
		/*MessageParams messageParams = new MessageParams();
		messageParams.setMessageType(MessageType.SYSTEM_MARK);
		messageParams.setReceiveUserIds(userList);
		messageParams.setMessage(message);
		messageService.createMessage(messageParams);*/
	}

	public void warnUser(Integer userId, String message) {
		Set<Integer> userList = new HashSet<Integer>();
		userList.add(userId);
		/*MessageParams messageParams = new MessageParams();
		messageParams.setMessageType(MessageType.SYSTEM_WARN);
		messageParams.setReceiveUserIds(userList);
		messageParams.setMessage(message);
		messageService.createMessage(messageParams);*/
	}

	public void delete(Integer userId) {
		UserQuery query = new UserQuery();
		query.setUserId(userId.toString());
		this.userMapper.delete(query);
		//加入黑名单缓存
		BlackUserCache.AddUser(userId);
	}
}
