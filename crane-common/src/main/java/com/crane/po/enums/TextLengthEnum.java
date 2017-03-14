package com.crane.po.enums;

/**
 * 
 * ClassName: 积分
 * date: 2015年8月9日 下午12:04:13 
 * @author 多多洛
 * @version 
 * @since JDK 1.7
 */
public enum TextLengthEnum {
	TEXT(65535l, "MYSQL text的长度"), MEDIUMTEXT(16777215l, "MYSQL mediumtext的长度"), LONGTEXT(4294967295l,
			"MYSQL longtext的长度"), LENGTH_500(500L, "500长度"), LENGTH_150(150L, "150长度"), LENGTH_200(200L, "200长度"), LENGTH_300(
			300L, "300长度"), LENGTH_50(50L, "50长度"), LENGTH_10(10L, "10长度");

	private Long length;
	private String desc;

	private TextLengthEnum(Long length, String desc) {
		this.length = length;
		this.desc = desc;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
