package com.crane.controller.spitslot;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crane.controller.BaseController;
import com.crane.po.model.SpitSlot;
import com.crane.po.model.SpitSlotComment;
import com.crane.po.query.SpitSlotQuery;
import com.crane.po.vo.AjaxResponse;
import com.crane.po.vo.PaginationResult;
import com.crane.service.SpitSlotService;
import com.crane.service.UserService;

import net.sf.ehcache.CacheManager;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月25日 下午9:24:42
* 
*/
@Controller
public class SpitSlotController extends BaseController {

	Logger logger = LoggerFactory.getLogger(SpitSlotController.class);
	
	@Resource
	private UserService usserService;
	@Resource
	private SpitSlotService spitSlotService;
	
    @RequestMapping(value = "spitslot")
    public ModelAndView spitSlot() {
        ModelAndView view = new ModelAndView("/page/spitslot/spitslot");
        List<SpitSlot> list = spitSlotService.findActiveUser4SpitSlot();
        view.addObject("list", list);
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "loadSpitSlot")
    public AjaxResponse<PaginationResult<SpitSlot>> loadSpitSlot(SpitSlotQuery query){
    	return null;
    }
    
    @ResponseBody
    @RequestMapping(value = "loadSpitSlotComment")
    public AjaxResponse<List<SpitSlotComment>> loadSpitSlotComment(Integer spitSlotId) {
    	return null;
    }
    
    @ResponseBody
    @RequestMapping(value = "addSpitSlot.action")
    public AjaxResponse<SpitSlot> addSpitSlot(HttpSession session, SpitSlot slot) {
        return null;
    }
    
    @ResponseBody
    @RequestMapping(value = "addSpitSlotComment.action")
    public AjaxResponse<SpitSlotComment> addSpitSlotComment(HttpSession session,SpitSlotComment comment){
    	return null;
    }
}
