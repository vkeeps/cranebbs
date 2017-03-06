package com.crane.po.vo;

import java.util.ArrayList;
import java.util.List;

import com.crane.po.enums.ResponseCode;

/**
 * 
 * ClassName: PaginationResult
 * date: 2015年8月9日 下午12:06:48 
 * @author 多多洛
 * @version @param <T>
 * @since JDK 1.7
 */
public class PaginationResult<T> {
	private SimplePage page;
	private List<T> list = new ArrayList<T>();
	private ResponseCode ResponseCode;// 500 服务器错误
	private StringBuilder msg = null;

	public PaginationResult(SimplePage page, List<T> list, ResponseCode ResponseCode, StringBuilder msg) {
		this.list = list;
		this.page = page;
		this.ResponseCode = ResponseCode;
		this.msg = msg;
	}

	public PaginationResult(SimplePage page, List<T> list) {
		this.list = list;
		this.page = page;
	}

	public PaginationResult(List<T> list, ResponseCode ResponseCode, StringBuilder msg) {
		this.list = list;
		this.ResponseCode = ResponseCode;
		this.msg = msg;
	}

	public PaginationResult(ResponseCode ResponseCode) {
		this.ResponseCode = ResponseCode;
	}

	public PaginationResult(ResponseCode ResponseCode, StringBuilder msg) {
		this.ResponseCode = ResponseCode;
		this.msg = msg;
	}

	public PaginationResult() {

	}

	public SimplePage getPage() {
		return page;
	}

	public void setPage(SimplePage page) {
		this.page = page;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public ResponseCode getResponseCode() {
		return ResponseCode;
	}

	public void setResponseCode(ResponseCode ResponseCode) {
		this.ResponseCode = ResponseCode;
	}

	public StringBuilder getMsg() {
		return msg;
	}

	public void setMsg(StringBuilder msg) {
		this.msg = msg;
	}
}
