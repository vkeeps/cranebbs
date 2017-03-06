/**
 * Project Name:ulewo-common
 * File Name:DateEnum.java
 * Package Name:com.ulewo.po.enums
 * Date:2015年9月26日上午8:55:22
 * Copyright (c) 2015, ulewo.com All Rights Reserved.
 *
*/

package com.crane.utils;

/**
 * ClassName:DateEnum <br/>
 * Date:     2015年9月26日 上午8:55:22 <br/>
 * @author   多多洛
 * Copyright (c) 2015, ulewo.com All Rights Reserved. 
 */
public enum DateTimePatternEnum {
	YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"), YYYYMM("yyyyMM"), YYYY("YYYY"), MM_POINT_DD("MM.dd"), YYYY_MM_DD(
			"yyyy-MM-dd");

	private String pattern;

	private DateTimePatternEnum(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
