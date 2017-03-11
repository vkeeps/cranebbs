package com.crane.utils;

import java.security.MessageDigest;

public class PasswordUtils {

	/**
	 * 采用MD5加密
	 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };
	
	private static String algorithm = "MD5"; //加密方式：MD5,SHA，默认设置MD5

	public static String encode(String salt,String originalPass) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			// 加密后的字符串
			result = byteArrayToHexString(md.digest(mergePasswordAndSalt(salt,originalPass).getBytes("utf-8")));
		} catch (Exception ex) {
		}
		return result;
	}

	/**
	 * 盐值加强
	 * @param password
	 * @return
	 */
	private static String mergePasswordAndSalt(String salt,String originalPass) {
		if (originalPass == null) {
			originalPass = "";
		}

		if ((salt == null) || "".equals(salt)) {
			return originalPass;
		} else {
			return originalPass + "{" + salt.toString() + "}";
		}
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * 加密后密码和原密码对比是否一致
	 * @param encPass
	 * @param rawPass
	 * @return
	 */
	public static boolean isPasswordValid(String encPass,String salt, String originalPass) {
		String pass1 = "" + encPass;
		String pass2 = encode(salt,originalPass);

		return pass1.equals(pass2);
	}

	
	
	
	/**
	 * 构造器
	 * @param salt
	 * @param algorithm 加密方式：MD5或者SHA
	 */
	/*public PasswordUtils(Object salt, String algorithm) {
		this.salt = salt;
		this.algorithm = algorithm;
	}*/
	/* public static void main(String[] args) {
	     String salt = "124213sqrfwqadas";
	     PasswordUtils encoderMd5 = new PasswordUtils(salt, "MD5");
	     String encode = encoderMd5.encode("crane");
	     System.out.println(encode);
	     boolean passwordValid = encoderMd5.isPasswordValid("ba01ff97d4fb003c38f070e9d76650ea", "crane");
	     System.out.println(passwordValid);
	 
	     PasswordEncoder encoderSha = new PasswordEncoder(salt, "SHA");
	     String pass2 = encoderSha.encode("test");
	     System.out.println(pass2);
	     boolean passwordValid2 = encoderSha.isPasswordValid("1bd98ed329aebc7b2f89424b5a38926e", "test");
	     System.out.println(passwordValid2);
	 }*/
}