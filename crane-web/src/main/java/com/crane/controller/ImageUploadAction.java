package com.crane.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
* @author  Crane:
* @version 5.0
* @time 2017年4月24日 下午6:09:15
* 
*/
@Controller
public class ImageUploadAction {

	private final static int MAX_FILE = 1024 * 1024 * 2;

    private final static int MAX_FILE_MAX = 1024 * 1024 * 3;

    private final static int TEMP_IMG_MAX_LENGTH = 1000;
    private final static int TEMP_IMG_MIN_LENGTH = 180;

    //常用网络图片高宽
    private final static int WEB_IMAGE_WIDTH = 780;
    
    private Logger log = LoggerFactory.getLogger(ImageUploadAction.class);
    
    @ResponseBody
    @RequestMapping(value = "/spitImageUpload.action")
    public Map<String,Object> spitImageUpload(HttpSession session, MultipartHttpServletRequest request, HttpServletResponse response){
    	Map<String, Object> map = new HashMap<String, Object>();
    	log.info("success");
    	return null;
    }
    
}
