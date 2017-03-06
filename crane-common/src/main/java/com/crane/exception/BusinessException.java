package com.crane.exception;

/**
 * 
 * ClassName: BusinessException
 * date: 2015年8月9日 下午12:02:55 
 * @author 多多洛
 * @version 
 * @since JDK 1.7
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 2874310081615076500L;

	public BusinessException(String message, Throwable e) {
		super(message, e);
	}

	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(Throwable e) {
		super(e);
	}

	/**
	 * TODO 重写fillInStackTrace 业务异常不需要堆栈信息，提高效率.
	 * @see java.lang.Throwable#fillInStackTrace()
	 */
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}
