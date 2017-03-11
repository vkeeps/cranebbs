
package com.crane.utils;

/**
 * ClassName:Constants <br/>
 * Date:     2015年9月19日 下午5:24:42 <br/>
 * @author   多多洛
 * Copyright (c) 2015, ulewo.com All Rights Reserved. 
 */
public class Constants {
	public static final int LENGTH_20 = 20, LENGTH_6 = 6, LENGTH_16 = 16;

	//用户头像和背景图，images文件夹下
	public static final String USER_IMG_PATH_USER_ICON = "usericon", USER_IMG_PATH_USER_BG = "/resource/images/defbg/bg";

	public static final String PATH_UPLOAD = "/upload/";

	public static final String PATH_AVATARS_SUFFIX = "avatars/";

	public static final String PATH_AVATARS = "/upload/avatars/";

	public static final String PATH_TEMP_UPLOAD = "/upload/temp/";

	public static final String PATH_DEFAULT_USER_ICON = "/resource/images/defusericon/";

	public static final String IMAGE_SUFFIX_JPG = ".jpg";

	public static final String IMAGE_SUFFIX_PNG = ".png";

	public static final String CHECK_CODE_KEY = "check_code";

	public static final String SESSION_USER_KEY = "session_user_key";

	public static final String SESSION_ERROR_LOGIN_COUNT = "session_error_login_count";

	public static final int MAX_LOGIN_ERROR_COUNT = 3;

	
	/**
	 * 连续签到天数
	 */
	public final static int CONTINUESIGINCOUNT = 7;

	/**
	 * Cookie UserInfo
	 */
	public final static String COOKIE_USER_INFO = "cookie4UserInfo";
	
	/**
	 * 服务器绝对路径
	 */
	public final static String ABSOLUTEPATH = "absolutePath";
	
	public final static int EXAM_MAX_TITLE = 50;

	public final static String ERROR_404 = "/error404";
	
	//模块的分类
	public final static String CACHE_KEY_BBS_CATEGORY = "bbsCategoryCache";
	public final static String CACHE_KEY_EXAM_CATEGORY = "examCategoryCache";
	public final static String CACHE_KEY_KNOWLEDGE_CATEGORY = "knowledgeCategoryCache";
	public final static String CACHE_KEY_ASK_CATEGORY = "askCategoryCache";
	public final static String CACHE_KEY_CATEOGRY = "categoryCache";
	
	//结果的判断
	public final static String Y = "Y";
	public final static String N = "N";

	/**
	 * 生成缩略图的大小
	 */
	public static final int THUMBNAILWIDTH = 150, THUMBNAILHEIGHT = 100;

	/**
	 * 生成缩略图的张数
	 */
	public static final int MAXTHUMBNAILCOUNT = 3;

}
