package com.crane.po.vo;

import com.crane.po.enums.PageSize;

/**
 * 
 * ClassName: SimplePage
 * date: 2015年8月9日 下午12:06:56 
 * @author 多多洛
 * @version 
 * @since JDK 1.7
 */
public class SimplePage {
	private int page;//当前第几页
	private int countTotal;//全部总数
	private int pageSize;//每页的个数
	private int pageTotal;//每页的总数
	private int start;//每页开始的第一个的序号(对应数据库，例如第一页第一条记录的序号是0)
	private int end;//每页结束的最后一个的序号(对应数据库，例如第一页第20条记录的序号是19
					//但是,数据库查询要取到20记录是需要是查到序号为limit 0,20，不取序号为20的记录，取前面的0-19
					//所以20为转换过来的是对应第20条记录.因此，第二页的第一条记录要从20号查起,如果要再想查20条记录,
					//那么，第二页最后一条对应的数据库序号就是20(第一条序号)+20(当页的需要的数量),也就是换成页面的第21条到第40条)

	public SimplePage() {
	};

	public SimplePage(Integer page, int countTotal, int pageSize) {
		if (null == page) {
			page = 0;
		}
		this.page = page;
		this.countTotal = countTotal;
		this.pageSize = pageSize;
		action();
	}

	public SimplePage(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void action() {
		if (this.pageSize <= 0) {
			//设置默认
			this.pageSize = PageSize.SIZE20.getSize();
		}
		if (this.countTotal > 0) {
			this.pageTotal = this.countTotal % this.pageSize == 0 ? this.countTotal / this.pageSize : this.countTotal
					/ this.pageSize + 1;
		} else {
			pageTotal = 1;
		}

		if (page <= 1) {
			page = 1;
		}
		if (page > pageTotal) {
			page = pageTotal;
		}
		this.start = (page - 1) * pageSize;
		this.end = this.pageSize;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCountTotal() {
		return countTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setCountTotal(int countTotal) {
		this.countTotal = countTotal;
		this.action();
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
