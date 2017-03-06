package com.test.oldsubject.thread;
/**
 * 存钱进程
* @author  Crane:
* @version 5.0
* @time 2017年3月3日 下午9:13:23
* 
*/
public class AddMoneyThread implements Runnable{
	
	private Account account;//银行账户
	private double money;
	
	public AddMoneyThread(Account account, double money) {
		super();
		this.account = account;
		this.money = money;
	}

	@Override
	public void run() {
		account.deposit(money);
	}

}
