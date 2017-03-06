package com.crane.po.query;

import com.crane.po.vo.SimplePage;

public class BaseQuery {
	private SimplePage page;
	private Integer pageNo;

	public SimplePage getPage() {
		return page;
	}

	public void setPage(SimplePage page) {
		this.page = page;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

}
