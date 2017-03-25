package com.crane.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月12日 下午5:33:51
* 
*/
@Controller
public class HomeController extends BaseController {

	@RequestMapping(value = "/")
	public ModelAndView index(HttpSession session) {
		ModelAndView view = new ModelAndView("/page/index");
		return view;
	}
	
}
