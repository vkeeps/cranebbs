package com.test.oldsubject.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行账户
 * 
 * @author Crane:
 * @version 5.0
 * @time 2017年3月3日 下午9:07:12
 * 
 */
public class Account {
	private Lock accountLock = new ReentrantLock();
	private double balance; // 账户余额

	/**
	 * 银行账户类(lock同步)
	 * 
	 * @param money
	 *            存入金额
	 */
	public void deposit(double money) {
		accountLock.lock();
		try{
			double newBalance = balance + money;
			try {
				Thread.sleep(10); // 模拟此业务需要一段时间处理
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			balance = newBalance;
		}finally{
			accountLock.unlock();
		}
		
	}
	
	/**
	 * 银行账户类(未同步)
	 * 
	 * @param money
	 *            存入金额
	 */
	/*public void deposit(double money) {
		double newBalance = balance + money;
		try {
			Thread.sleep(10); // 模拟此业务需要一段时间处理
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		balance = newBalance;
	}*/
	
	/**
	 * 银行账户类(synchronized同步)
	 * 
	 * @param money
	 *            存入金额
	 */
	/*
	public synchronized void deposit(double money) {
		double newBalance = balance + money;
		try {
			Thread.sleep(10); // 模拟此业务需要一段时间处理
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		balance = newBalance;
	}*/

	public double getBalance() {
		return balance;
	}

}
