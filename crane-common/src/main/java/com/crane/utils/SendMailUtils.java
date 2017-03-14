package com.crane.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendMailUtils {

	/**
	 * 
	 * @param title  邮件标题
	 * @param content  邮件内容
	 * @param toEmilAddress  收件人地址
	 * @throws Exception
	 */
	public static void sendEmail(String sendEmail, String sendEmailPwd, String title, String content,
			   String[] toEmilAddress) throws Exception {

		 /* Properties props = new Properties();
		  props.setProperty("mail.smtp.auth", "true");
		  props.setProperty("mail.transport.protocol", "smtp");
		  //添加SSL认证
		  props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");*/
			Properties props = new Properties();
			// 开启debug调试，以便在控制台查看
			props.setProperty("mail.debug", "true");
			// 设置邮件服务器主机名
			props.setProperty("mail.host", "smtp.qq.com");
			// 发送服务器需要身份验证
			props.setProperty("mail.smtp.auth", "true");
			// 发送邮件协议名称
			props.setProperty("mail.transport.protocol", "smtp");
			
			// 开启SSL加密，否则会失败
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);
				
			Session session = Session.getInstance(props);
			Message msg = new MimeMessage(session);
			  // 发送的邮箱地址
			msg.setFrom(new InternetAddress(sendEmail));
			  // 设置标题
			msg.setSubject(title);
			  // 设置内容
			msg.setContent(content, "text/html;charset=gbk;");
			Transport transport = session.getTransport();
			  // 设置服务器以及账号和密码
			  //这里端口改成465
			transport.connect("smtp.qq.com", sendEmail, sendEmailPwd);
			  // 发送到的邮箱地址
			transport.sendMessage(msg, getAddress(toEmilAddress));
			transport.close();
	 }
	
	

	private static Address[] getAddress(String[] emilAddress) throws Exception {

		Address[] address = new Address[emilAddress.length];
		for (int i = 0; i < address.length; i++) {
			address[i] = new InternetAddress(emilAddress[i]);
		}
		return address;
	}
	
	/*public static void sendEmail(String sendEmail, String sendEmailPwd, String title, String content,
	String[] toEmilAddress) throws Exception {

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		// 发送的邮箱地址
		msg.setFrom(new InternetAddress(sendEmail));
		// 设置标题
		msg.setSubject(title);
		// 设置内容
		msg.setContent(content, "text/html;charset=gbk;");
		Transport transport = session.getTransport();
		// 设置服务器以及账号和密码
		transport.connect("smtp.qq.com", 25, sendEmail, sendEmailPwd);
		// 发送到的邮箱地址
		transport.sendMessage(msg, getAddress(toEmilAddress));
		transport.close();
	}*/
	
	
	/*public static void main(String[] args) {
		  try {
		   sendEmail("100010@qq.com", "xxxxx", "121212", "xx", new String[] { "100010@qq.com" });
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	 }*/
	
	/*public static void sendEmail(String sendEmail, String sendEmailPwd, String title, String content,
	   String[] toEmilAddress) throws Exception {
		// 创建Properties 类用于记录邮箱的一些属性
		Properties props = new Properties();
		// 表示SMTP发送邮件，必须进行身份验证
		props.put("mail.smtp.auth", "true");
		//此处填写SMTP服务器
		props.put("mail.smtp.host", "smtp.qq.com");
		//端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
		props.put("mail.smtp.port", "587");
		// 此处填写你的账号
		props.put("mail.user", "873330416@qq.com");
		// 此处的密码就是前面说的16位STMP口令
		props.put("mail.password", "rhjhjgxuujzabcbe");
		
		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
		
		 protected PasswordAuthentication getPasswordAuthentication() {
		     // 用户名、密码
		     String userName = props.getProperty("mail.user");
		     String password = props.getProperty("mail.password");
		     return new PasswordAuthentication(userName, password);
		 }
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(
		     props.getProperty("mail.user"));
		message.setFrom(form);
		
		// 设置收件人的邮箱
		InternetAddress to = new InternetAddress("409716474@qq.com");
		message.setRecipient(RecipientType.TO, to);
		
		// 设置邮件标题
		message.setSubject("测试邮件");
		
		// 设置邮件的内容体
		message.setContent("这是一封测试邮件", "text/html;charset=UTF-8");
		
		// 最后当然就是发送邮件啦
		Transport.send(message);
	}*/
	
	/*public static void sendEmail(String sendEmail, String sendEmailPwd, String title, String content,
		   String[] toEmilAddress) throws Exception {
	
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		//添加SSL认证
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(props);
		Message msg = new MimeMessage(session);
		// 发送的邮箱地址
		msg.setFrom(new InternetAddress(sendEmail));
		// 设置标题
		msg.setSubject(title);
		// 设置内容
		msg.setContent(content, "text/html;charset=gbk;");
		Transport transport = session.getTransport();
		// 设置服务器以及账号和密码
		//这里端口改成465
		transport.connect("smtp.exmail.qq.com", 587, sendEmail, sendEmailPwd);
		// 发送到的邮箱地址
		transport.sendMessage(msg, getAddress(toEmilAddress));
		transport.close();
	}*/
	
	/*public static void sendEmail(String sendEmail, String sendEmailPwd, String title, String content,
		String[] toEmilAddress) throws Exception {
		
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		// 发送的邮箱地址
		msg.setFrom(new InternetAddress(sendEmail));
		// 设置标题
		msg.setSubject(title);
		// 设置内容
		msg.setContent(content, "text/html;charset=gbk;");
		Transport transport = session.getTransport();
		// 设置服务器以及账号和密码
		transport.connect("smtp.qq.com", 25, sendEmail, sendEmailPwd);
		// 发送到的邮箱地址
		transport.sendMessage(msg, getAddress(toEmilAddress));
		transport.close();
	}*/
	
	/*public static void main(String[] args) {
		try {
		sendEmail("100010@qq.com", "xxxxx", "121212", "xx", new String[] { "100010@qq.com" });
		} catch (Exception e) {
		e.printStackTrace();
		}
	}*/	
}
