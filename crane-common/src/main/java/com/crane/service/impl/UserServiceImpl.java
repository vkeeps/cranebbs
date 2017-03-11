package com.crane.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crane.exception.BusinessException;
import com.crane.mapper.UserMapper;
import com.crane.po.model.User;
import com.crane.po.query.UserQuery;
import com.crane.po.vo.PaginationResult;
import com.crane.service.UserService;
import com.crane.utils.Constants;
import com.crane.utils.FileUtils;
import com.crane.utils.PasswordUtils;
import com.crane.utils.ServerUtils;
import com.crane.utils.StringTools;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月10日 下午6:03:30
* 
*/
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper<User, UserQuery> userMapper;

	@Override
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
		 * 校验用户是否存在
		 */
		if(this.findUserByUserName(userName) != null){
			throw new BusinessException("用户已存在");
		}
		/**
		 * 
		 * 校验邮箱是否已经存在
		 */
		if (this.findUserByEmail(user.getEmail()) != null) {
			throw new BusinessException("邮箱已被使用");
		}
		//密码加密:MD5
		user.setPassword(PasswordUtils.encode(userName,password));
		user.setUserBg(Constants.USER_IMG_PATH_USER_BG + ((int) (Math.random() * 10) + 1) + Constants.IMAGE_SUFFIX_JPG);
		Date curDate = new Date();
		user.setRegisterTime(curDate);
		user.setLastLoginTime(curDate);
		this.userMapper.insert(user);
		
		//复制头像，更新用户头像信息
		String icon = Constants.USER_IMG_PATH_USER_ICON + ((int) (Math.random() * 10) + 1) + ".png";
		String targetIcon = user.getUserId() + ".jpg";
		copyUserIcon(icon, targetIcon);
		user.setUserIcon(Constants.PATH_AVATARS_SUFFIX + targetIcon);
		this.updateInfo(user);
		
	}
	/**
	 * 复制，更新用户头像
	 */
	public void copyUserIcon(String sourceIcon, String targetIcon) {
		File sourcefile = new File(ServerUtils.getRealPath() + Constants.PATH_DEFAULT_USER_ICON + sourceIcon);
		File targetFile = new File(ServerUtils.getRealPath() + Constants.PATH_AVATARS + targetIcon);
		FileUtils.copyFile(sourcefile, targetFile);
	}

	@Override
	public User findUserByUserName(String userName) {
		UserQuery query = new UserQuery();
		query.setUserName(userName);
		List<User> userList = userMapper.selectList(query);
		if (userList.size() == 1) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
		UserQuery query = new UserQuery();
		query.setEmail(email);
		List<User> userList = userMapper.selectList(query);
		if(userList.size() ==1){
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

	/**
	 * 用户登录
	 * account 帐号
	 */
	@Override
	public User login(String account, String password) throws BusinessException {
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
		if (!PasswordUtils.isPasswordValid(user.getPassword(),user.getUserName(),password)) {
			throw new BusinessException("密码错误");
		}
		updateLastLoginTime(user.getUserId());
		return user;
	}

	/**
	 * 发送验证码到邮箱
	 */
	@Override
	public void sendCheckCode(String email) throws BusinessException {
		if(StringTools.isEmpty(email)){
			throw new BusinessException("请求参数错误");
		}
		User user = this.findUserByEmail(email);
		if (null == user) {
			throw new BusinessException("输入的邮箱不存在");
		}
	}

	@Override
	public void resetPwd(String email, String password, String checkCode) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public int changeMark(Integer userId, Integer mark) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateInfo(User user) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLastLoginTime(Integer userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePwd(Integer userId, String oldPwd, String newPwd) throws BusinessException {
		// TODO Auto-generated method stub

	}


	@Override
	public PaginationResult<User> findUserByPage(UserQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rewardMark(Integer userId, Integer mark, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warnUser(Integer userId, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer userId) {
		// TODO Auto-generated method stub

	}

}
