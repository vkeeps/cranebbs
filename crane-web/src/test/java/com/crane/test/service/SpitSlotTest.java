package com.crane.test.service;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.crane.mapper.SpitSlotCommentMapper;
import com.crane.po.model.SpitSlot;
import com.crane.po.model.SpitSlotComment;
import com.crane.po.query.SpitSlotQuery;
import com.crane.po.vo.PaginationResult;
import com.crane.po.vo.SimplePage;
import com.crane.service.SpitSlotService;

/**
* @author  Crane:
* @version 5.0
* @time 2017年4月18日 下午8:54:54
* 
*/
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class SpitSlotTest extends AbstractTestNGSpringContextTests {
		@Resource
		private SpitSlotService spitSlotService;
	
		@Resource
		private SpitSlotCommentMapper<SpitSlotComment, Integer> spitSlotCommentMapper;
	
		@Test
		public void selectListTest() {
			SpitSlotQuery query = new SpitSlotQuery();
			SimplePage page = new SimplePage();
			page.setStart(0);
			page.setEnd(10);
			query.setPage(page);
			PaginationResult<SpitSlot> s = spitSlotService.findSpitSlotList(query);
			logger.info(s.getList().get(0)+"---666");
		}
		/*@Test
		public void addTest() throws BusinessException{
			SpitSlot spitSlot = new SpitSlot();
			spitSlot.setUserId(10000);
			spitSlot.setUserIcon("avatars/10000.jpg");
			spitSlot.setUserName("crane");
			spitSlot.setContent("wadawd");
			spitSlotService.addSpitSlot(spitSlot);
		}*/
}
