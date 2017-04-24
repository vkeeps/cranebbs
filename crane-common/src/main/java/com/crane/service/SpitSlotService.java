package com.crane.service;

import java.util.List;

import com.crane.exception.BusinessException;
import com.crane.po.model.SpitSlot;
import com.crane.po.model.SpitSlotComment;
import com.crane.po.query.SpitSlotQuery;
import com.crane.po.vo.PaginationResult;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月29日 下午6:13:36
* 
*/
public interface SpitSlotService {
	
	/**
	 * 添加吐槽
	 * @param slot
	 * @throws BusinessException
	 */
	public void addSpitSlot(SpitSlot slot) throws BusinessException;
	
	/**
	 * 分页查询吐槽
	 * @param query
	 * @return
	 */
	public PaginationResult<SpitSlot> findSpitSlotList(SpitSlotQuery query);
	public PaginationResult<SpitSlot> findSpitSlotListNoComment(SpitSlotQuery query);
	
	/**
	 * 查询吐槽详情
	 * @param userId
	 * @param id
	 * @return
	 */
	public SpitSlot findSpitSlot(Integer userId,Integer id);
	public SpitSlot findSpitSlotById(Integer id);
	public SpitSlot findSpitSlotNoCommentId(Integer id);
	
	 /**
     * addSpitSlotComment:(添加评论). <br/>
     * @param slot
     * @exception BusinessException
     * @since JDK 1.7
     */
    public void addSpitSlotComment(SpitSlotComment slot) throws BusinessException;

    /**
     * loadSpitSlotComment:(查询所有评论). <br/>
     * @param spitSlotId
     * @return
     * @exception BusinessException
     * @since JDK 1.7
     */
    public List<SpitSlotComment> loadSpitSlotComment(Integer spitSlotId);

    /**
     * selectActiveUser4SpitSlot:(查询活跃用户). <br/>
     * @param query
     * @return
     * @since JDK 1.7
     */
    public List<SpitSlot> findActiveUser4SpitSlot();

    /**
     * findSpitSlotCommentList:(分页查询吐槽评论). <br/>
     * @param query
     * @return
     * @since JDK 1.7
     */
    public PaginationResult<SpitSlotComment> findSpitSlotCommentList(SpitSlotQuery query);

    /***
     * deleteSpitSlot:(删除吐槽). <br/>
     * @param spitSlotId
     * @since JDK 1.7
     */
    public void deleteSpitSlot(Integer spitSlotId);

    /**
     * deleteSpitSlotComment:(删除评论). <br/>
     * @param commentId
     * @since JDK 1.7
     */
    public void deleteSpitSlotComment(Integer spitSlotId, Integer commentId);
}
