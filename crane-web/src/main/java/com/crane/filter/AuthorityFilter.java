package com.crane.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crane.cache.BlackUserCache;
import com.crane.po.enums.ResponseCode;
import com.crane.po.model.SessionUser;
import com.crane.po.model.User;
import com.crane.po.vo.AjaxResponse;
import com.crane.service.UserService;
import com.crane.service.impl.UserServiceImpl;
import com.crane.utils.Constants;
import com.crane.utils.JacksonMapper;
import com.crane.utils.ServerUtils;
import com.crane.utils.SpringContextUtil;

public class AuthorityFilter implements Filter {
	private final static String[] static_ext = { "js", "css", "jpg", "png", "gif", "ico", "swf" };
	private final static String action_ext = "action";
	private final static String[] static_ext_file = { "zip", "rar", "html" };
	private static String absolutePath = null;
	private final static String MANAGER_URL = "manage";
	private final static String RESOURCE = "resource";
	private final static Integer MANAGER_UID = 10001;
	private Logger logger = LoggerFactory.getLogger(AuthorityFilter.class);

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		ServletContext application = request.getSession().getServletContext();
		String req_uri = request.getRequestURI();
		HttpSession session = request.getSession();
		String type = req_uri.substring(req_uri.lastIndexOf('.') + 1);

		// 静态资源忽略
		if (ArrayUtils.contains(static_ext, type)) {
			chain.doFilter(req, resp);
			return;
		}

		if (ArrayUtils.contains(static_ext_file, type) && !req_uri.contains(RESOURCE)) {
			response.sendRedirect(Constants.ERROR_404);
			return;
		}

		if (absolutePath == null) {
			absolutePath = getRealPath(request);
			ServerUtils.setDomain(absolutePath);
		}
		if (application.getAttribute(Constants.ABSOLUTEPATH) == null) {
			application.setAttribute(Constants.ABSOLUTEPATH, absolutePath);
		}
		Object sessionUserObj = session.getAttribute(Constants.SESSION_USER_KEY);
		//自动登陆
		if (null == sessionUserObj) {
			autoLogin(request, response);
			sessionUserObj = session.getAttribute(Constants.SESSION_USER_KEY);
		}

		//过滤管理后台只能由指定用户访问	
		if (req_uri.contains(MANAGER_URL)
				&& (sessionUserObj == null || ((SessionUser) sessionUserObj).getUserId().intValue() != MANAGER_UID)) {
			response.sendRedirect(ServerUtils.getDomain());//跳转到首页
			return;
		}

		//过滤.action后缀的请求
		if (action_ext.equals(type)) {
			if (null == sessionUserObj) {
				if (request.getHeader("x-requested-with") != null
						&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // ajax请求
					response.setHeader("Content-Type", "text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					AjaxResponse<?> ajaxResponse = new AjaxResponse<Object>();
					ajaxResponse.setResponseCode(ResponseCode.LOGINTIMEOUT);
					ajaxResponse.setErrorMsg("登录超时!");
					writer.print(JacksonMapper.toJson(ajaxResponse));
				} else {
					response.sendRedirect("/login");
				}
				return;
			} else {
				SessionUser sessionUser = (SessionUser) sessionUserObj;
				if (BlackUserCache.getUser(sessionUser.getUserId()) != null) {
					PrintWriter writer = response.getWriter();
					AjaxResponse<?> ajaxResponse = new AjaxResponse<Object>();
					ajaxResponse.setResponseCode(ResponseCode.NOPERMISSION);
					ajaxResponse.setErrorMsg("你已经被拉黑，没有权限操作");
					writer.print(JacksonMapper.toJson(ajaxResponse));
					return;
				}
			}
		}
		chain.doFilter(request, resp);
		return;
	}

	private String getRealPath(HttpServletRequest request) {
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String realPath = "http://" + request.getServerName() + port + request.getContextPath();
		return realPath;
	}

	private void autoLogin(HttpServletRequest req, HttpServletResponse response) {
		try {
			Cookie cookieInfo = getCookieByName(req, Constants.COOKIE_USER_INFO);
			if (cookieInfo != null) {
				String info = URLDecoder.decode(cookieInfo.getValue(), "utf-8");
				if (info != null && !"".equals(info)) {
					String infos[] = info.split("\\|");

					UserService userService = (UserServiceImpl) SpringContextUtil.getBean("userService");
					User user = userService.login(infos[0],infos[1]);
					if (user != null) {
						SessionUser loginUser = new SessionUser();
						loginUser.setUserId(user.getUserId());
						loginUser.setUserName(user.getUserName());
						loginUser.setUserIcon(user.getUserIcon());
						req.getSession().setAttribute(Constants.SESSION_USER_KEY, loginUser);
					}
				}
			}
		} catch (Exception e) {
			//清楚cookie信息
			Cookie cookie = new Cookie(Constants.COOKIE_USER_INFO, null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			logger.error("自动登陆失败：", e);
		}

	}

	public Cookie getCookieByName(HttpServletRequest request, String name) {

		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	private Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {

		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
