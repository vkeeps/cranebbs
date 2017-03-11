package com.crane.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.WordFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crane.service.UserService;
import com.crane.utils.Constants;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月8日 下午9:18:29
* 
*/
@Controller
public class UserController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
}
