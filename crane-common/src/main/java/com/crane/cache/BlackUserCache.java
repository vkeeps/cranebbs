package com.crane.cache;

import java.util.HashMap;
import java.util.Map;

public class BlackUserCache {
	private static Map<Integer, Integer> blackUser = null;
	static {
		blackUser = new HashMap<Integer, Integer>();
	}

	public static void AddUser(Integer userId) {
		blackUser.put(userId, userId);
	}

	public static Integer getUser(Integer userId) {
		return blackUser.get(userId);
	}
}
