package com.crane.po.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * ClassName: ConfigInfo
 * date: 2015年8月9日 下午12:03:51 
 * @author 多多洛
 * @version 
 * @since JDK 1.7
 */
@Component("configInfo")
public class ConfigInfo {
	/**
	 *找回密码发送的邮箱地址
	 */
	@Value("#{applicationProperties['crane.email.findemail']}")
	private String findemail;

	/**
	 * 找回密码发送邮箱的密码
	 */
	@Value("#{applicationProperties['crane.emial.findpwd']}")
	private String findpwd;

	/*@Value("#{applicationProperties['crane.solr.server.url']}")
	private String solrServerUrl;

	@Value("#{applicationProperties['crane.solr.time.out']}")
	private int solrTimeOut;

	@Value("#{applicationProperties['crane.solr.max.totalconnections']}")
	private int maxTotalConnections;

	@Value("#{applicationProperties['crane.solr.max.connections.per.host']}")
	private int maxConnectionsPerHost;

	@Value("#{applicationProperties['crane.solr.open']}")
	private boolean openSolr;*/

	public String getFindemail() {
		return findemail;
	}

	public void setFindemail(String findemail) {
		this.findemail = findemail;
	}

	public String getFindpwd() {
		return findpwd;
	}

	public void setFindpwd(String findpwd) {
		this.findpwd = findpwd;
	}

	/*public String getSolrServerUrl() {
		return solrServerUrl;
	}

	public void setSolrServerUrl(String solrServerUrl) {
		this.solrServerUrl = solrServerUrl;
	}

	public int getSolrTimeOut() {
		return solrTimeOut;
	}

	public void setSolrTimeOut(int solrTimeOut) {
		this.solrTimeOut = solrTimeOut;
	}

	public int getMaxTotalConnections() {
		return maxTotalConnections;
	}

	public void setMaxTotalConnections(int maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	public int getMaxConnectionsPerHost() {
		return maxConnectionsPerHost;
	}

	public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
		this.maxConnectionsPerHost = maxConnectionsPerHost;
	}

	public boolean isOpenSolr() {
		return openSolr;
	}

	public void setOpenSolr(boolean openSolr) {
		this.openSolr = openSolr;
	}*/

}