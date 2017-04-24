package com.crane.controller.user;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crane.controller.BaseController;
import com.crane.exception.BusinessException;
import com.crane.po.enums.ResponseCode;
import com.crane.po.model.SessionUser;
import com.crane.po.model.User;
import com.crane.po.vo.AjaxResponse;
import com.crane.service.UserService;
import com.crane.utils.Constants;
import com.crane.utils.StringTools;
import com.crane.utils.Vcode.Captcha;
import com.crane.utils.Vcode.GifCaptcha;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月8日 下午9:18:29
* 
*/
@Controller
public class UserController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	/*@RequestMapping(value="login")
	public ModelAndView login(HttpServletRequest request,HttpSession session){
		ModelAndView view = new ModelAndView("/page/login");
		if (null != session.getAttribute(Constants.SESSION_ERROR_LOGIN_COUNT)
				&& (Integer) session.getAttribute(Constants.SESSION_ERROR_LOGIN_COUNT) >= Constants.MAX_LOGIN_ERROR_COUNT) {
			view.addObject("checkCode", "checkCode");
		}
		return view;
	}*/
	
	@RequestMapping(value="regist")
	public ModelAndView regist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("/page/regist");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "regist.do")
	public AjaxResponse<Object> registerDo(HttpSession session, User user, String checkCode){
		AjaxResponse<Object> result = new AjaxResponse<Object>();
		result.setResponseCode(ResponseCode.SUCCESS);
		try{
			String sessionChekCode = session.getAttribute(Constants.CHECK_CODE_KEY).toString();
			if(!sessionChekCode.equalsIgnoreCase(checkCode)){
				result.setErrorMsg("验证码错误");
				result.setResponseCode(ResponseCode.CODEERROR);
			}else{
				userService.regist(user);
				//注册之后保存用户信息，直接登录
				SessionUser sessionUser = new SessionUser();
				sessionUser.setUserId(user.getUserId());
				sessionUser.setUserIcon(user.getUserIcon());
				sessionUser.setUserName(user.getUserName());
				session.setAttribute(Constants.SESSION_USER_KEY, sessionUser);
				result.setData(user.getUserId());
			}
		}catch(BusinessException e){
			result.setErrorMsg(e.getMessage());
			result.setResponseCode(ResponseCode.BUSINESSERROR);
			logger.error("注册用户失败,用户名{},邮箱：{}", user.getUserName(), user.getEmail());
		}catch(Exception e){
			result.setErrorMsg(ResponseCode.SERVERERROR.getDesc());
			result.setResponseCode(ResponseCode.SERVERERROR);
			logger.error("注册用户失败,用户名{},邮箱：{}", user.getUserName(), user.getEmail());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "login.do")
	public AjaxResponse<Object> loginDo(HttpSession session, HttpServletResponse response, String account,
			String password, String rememberMe, String checkCode) {
		final String REMENBERME = "1";
		AjaxResponse<Object> result = new AjaxResponse<>();
		String jsessionId = session.getId();
		logger.info("请求的sessionId:{}", jsessionId);
		try{
			String sessionCheckCode = String.valueOf(session.getAttribute(Constants.CHECK_CODE_KEY));
			//验证验证码（输出错误超过3次时需要）
			if ((!StringTools.isEmpty(sessionCheckCode) && !sessionCheckCode.equalsIgnoreCase(checkCode))
					&& null != session.getAttribute(Constants.SESSION_ERROR_LOGIN_COUNT)
					&& (Integer) session.getAttribute(Constants.SESSION_ERROR_LOGIN_COUNT) >= Constants.MAX_LOGIN_ERROR_COUNT) {
				result.setErrorMsg("验证码错误");
				result.setResponseCode(ResponseCode.CODEERROR);
				return result;
			}
			User user = userService.login(account, password);
			result.setResponseCode(ResponseCode.SUCCESS);
			SessionUser sessionUser = new SessionUser();
			sessionUser.setUserId(user.getUserId());
			sessionUser.setUserIcon(user.getUserIcon());
			sessionUser.setUserName(user.getUserName());
			session.setAttribute(Constants.SESSION_USER_KEY, sessionUser);
			//记住登陆状态
			if(REMENBERME.equals(rememberMe)){
				// 自动登录，保存用户名密码到 Cookie
				String info = URLEncoder.encode(account.toString(), "utf-8")+user.getPassword();
				//清除之前的cookie
				Cookie cookie = new Cookie(Constants.COOKIE_USER_INFO, null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				// 建用户信息保存到Cookie中
				cookie = new Cookie(Constants.COOKIE_USER_INFO, info);
				cookie.setPath("/");
				// 设置最大生命周期为1年。
				cookie.setMaxAge(31536000);
				response.addCookie(cookie);
			}else{
				Cookie cookie = new Cookie(Constants.COOKIE_USER_INFO, null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}catch(BusinessException e){
			if (null == session.getAttribute(Constants.SESSION_ERROR_LOGIN_COUNT)) {
				session.setAttribute(Constants.SESSION_ERROR_LOGIN_COUNT, 1);
			} else {
				session.setAttribute(Constants.SESSION_ERROR_LOGIN_COUNT,
						(Integer) session.getAttribute(Constants.SESSION_ERROR_LOGIN_COUNT) + 1);
			}
			if ((Integer) session.getAttribute(Constants.SESSION_ERROR_LOGIN_COUNT) >= Constants.MAX_LOGIN_ERROR_COUNT) {
				result.setResponseCode(ResponseCode.MOREMAXLOGINCOUNT);
			} else {
				result.setResponseCode(ResponseCode.BUSINESSERROR);
			}
			result.setErrorMsg(e.getMessage());
			logger.error("登陆失败，账号：{}", account, e);
		}catch(Exception e){
			result.setErrorMsg(ResponseCode.SERVERERROR.getDesc());
			result.setResponseCode(ResponseCode.SERVERERROR);
			logger.error("登陆失败，账号：{}", account, e);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/logout")
	public AjaxResponse<Object> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		AjaxResponse<Object> result = new AjaxResponse<Object>();
		result.setResponseCode(ResponseCode.SUCCESS);
		Cookie cookie = new Cookie(Constants.COOKIE_USER_INFO, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		session.invalidate();
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="checkUserName.do")
	public AjaxResponse<Object> checkUserName(HttpSession session,HttpServletResponse response,String userName){
		AjaxResponse<Object> result  = new AjaxResponse<Object>();
		result.setResponseCode(ResponseCode.SUCCESS);
		try{
			User user = userService.findUserByUserName(userName);
			if(null!=user){
				result.setErrorMsg("用户已存在");
				result.setResponseCode(ResponseCode.BUSINESSERROR);
				return result;
			}else{
				return result;
			}
		}catch (Exception e) {
			result.setErrorMsg(ResponseCode.SERVERERROR.getDesc());
			result.setResponseCode(ResponseCode.SERVERERROR);
			logger.error("检查用户名失败{}",userName);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value="checkEmail.do")
	public AjaxResponse<Object> checkEmail(HttpSession session,HttpServletResponse response,String email){
		AjaxResponse<Object> result  = new AjaxResponse<Object>();
		result.setResponseCode(ResponseCode.SUCCESS);
		try{
			User user = userService.findUserByEmail(email);
			if(null!=user){
				result.setErrorMsg("邮箱已存在");
				result.setResponseCode(ResponseCode.BUSINESSERROR);
				return result;
			}else{
				return result;
			}
		}catch (Exception e) {
			result.setErrorMsg(ResponseCode.SERVERERROR.getDesc());
			result.setResponseCode(ResponseCode.SERVERERROR);
			logger.error("检查邮箱失败{}",email);
		}
		return result;
	}
	
	/**
	 * gif验证码
	 * @param response
	 * @param request
	 * @param session
	 */
	@RequestMapping(value="checkCode",method=RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request, HttpSession session){
		try{
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/gif");  
	        /**
	         * gif格式动画验证码
	         * 宽，高，位数。
	         */
	        Captcha captcha = new GifCaptcha(155,50,5);
	        //输出
	        captcha.out(response.getOutputStream());
	        //存入Session
	        session.setAttribute(Constants.CHECK_CODE_KEY,captcha.text().toLowerCase());  
		}catch(Exception e){
			logger.error("生成验证码失败");
		}
	}
}
