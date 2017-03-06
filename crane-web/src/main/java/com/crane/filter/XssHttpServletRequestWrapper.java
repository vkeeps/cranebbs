package com.crane.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import com.crane.utils.StringTools;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private static final String[] ESPECIALLY_PARAMS = { "richContent" };

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getQueryString() {
		return clearXss(super.getQueryString(), null);
	}

	@Override
	public String getParameter(String name) {
		return clearXss(super.getParameter(name), name);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				escapseValues[i] = clearXss(values[i], name);
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}

	private String clearXss(String str, String name) {
		if (StringTools.isEmpty(str) || ArrayUtils.contains(ESPECIALLY_PARAMS, name)) {
			return str;
		}
		str = StringEscapeUtils.escapeHtml4(str);
		str = StringTools.escapeHtml(str);
		return str;
	}
}