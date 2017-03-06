package com.crane.po.enums;

/**
 * 
 * ClassName: PageSize
 * date: 2015年8月9日 下午12:04:13 
 * @author 多多洛
 * @version 
 * @since JDK 1.7
 */
public enum PageSize {
	SIZE10(10), SIZE15(15), SIZE20(20), SIZE30(30), SIZE40(40), SIZE50(50);
	int size;

	private PageSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}
}
