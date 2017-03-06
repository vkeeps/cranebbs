/**
 * Project Name:ulewo-common
 * File Name:ServerUtils.java
 * Package Name:com.ulewo.utils
 * Date:2015年9月26日下午8:49:47
 * Copyright (c) 2015, ulewo.com All Rights Reserved.
 *
*/

package com.crane.utils;

/**
 * ClassName:ServerUtils <br/>
 * Date:     2015年9月26日 下午8:49:47 <br/>
 * @author   多多洛
 * Copyright (c) 2015, ulewo.com All Rights Reserved. 
 */
public class ServerUtils {
	/**
	 * 服务区项目路径
	 */
	private static String realPath = null;

	/**
	 * 服务器域名
	 */
	private static String domain = null;

	public static String getRealPath() {
		return realPath;
	}

	public static void setRealPath(String realPath) {
		ServerUtils.realPath = realPath;
	}

	public static String getDomain() {
		return domain;
	}

	public static void setDomain(String domain) {
		ServerUtils.domain = domain;
	}

}
