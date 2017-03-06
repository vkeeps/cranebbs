package com.test.oldsubject.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月3日 下午9:20:43
* 
*/
public class Test01 {
	public static void main(String[] args) {
		Account account = new Account();
		
		//创建线程池
		ExecutorService service = Executors.newFixedThreadPool(100);
		
		//模拟多线程发执行
		for(int i=0;i<100;i++){
			service.execute(new AddMoneyThread(account, 1));
		}
		service.shutdown();
		//线程服务在运行时判断
		while(!service.isTerminated()){
		}
		
		//线程运行结束后输出结果
		System.out.println("账户余额"+account.getBalance());
	}
}
