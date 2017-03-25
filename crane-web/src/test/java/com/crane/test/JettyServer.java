package com.crane.test;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月6日 下午8:52:03
* 
*/
public class JettyServer {

	static final String absloute_path = "E:\\workspace\\project3\\crane\\crane-web";
	
	public static void main(String[] args) throws Exception {
		Server server = buildNormalServer(8091, "/");
		server.start();
	}

	/**
	 * 创建用于正常运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server buildNormalServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new org.mortbay.jetty.webapp.WebAppContext(absloute_path+"\\src\\main\\webapp", contextPath);
		webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		webContext.setMaxFormContentSize(-1);
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}
	
}
