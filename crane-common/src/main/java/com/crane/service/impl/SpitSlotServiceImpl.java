package com.crane.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crane.exception.BusinessException;
import com.crane.mapper.SpitSlotCommentMapper;
import com.crane.mapper.SpitSlotMapper;
import com.crane.po.enums.MarkEnum;
import com.crane.po.enums.PageSize;
import com.crane.po.enums.SourceFrom;
import com.crane.po.enums.TextLengthEnum;
import com.crane.po.model.SpitSlot;
import com.crane.po.model.SpitSlotComment;
import com.crane.po.query.SpitSlotQuery;
import com.crane.po.vo.PaginationResult;
import com.crane.po.vo.SimplePage;
import com.crane.service.SpitSlotService;
import com.crane.service.UserService;
import com.crane.utils.ImageUtils;
import com.crane.utils.StringTools;

/**
 * @author Crane:
 * @version 5.0
 * @time 2017年3月29日 下午6:13:57
 * 
 */
@Service("spitSlotService")
public class SpitSlotServiceImpl implements SpitSlotService {

	@Autowired
	private SpitSlotMapper<SpitSlot, SpitSlotQuery> spitSlotMapper;
	@Autowired
	private SpitSlotCommentMapper<SpitSlotComment, SpitSlotQuery> spitSlotCommentMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private FormatAtService formatAtService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BusinessException.class)
	public void addSpitSlot(SpitSlot slot) throws BusinessException {
		String content = slot.getContent();
		if (StringTools.isEmpty(content) || content.length() > TextLengthEnum.TEXT.getLength()) {
			throw new BusinessException("参数错误");
		}
		// 判断阕值
		// thresholdService.canOp(slot.getUserId());

		content = StringTools.addLink(content);// 加上链接
		Set<Integer> receiveUserId = new HashSet<Integer>();
		// 格式化内容，加上@关注的人,格式化表情
		String formatContent = formatAtService.generateRefererLinks(content, receiveUserId, slot.getUserId());
		slot.setContent(formatContent);
		// 创建缩略图
		String thumbnail = ImageUtils.createThumbnail(slot.getImageUrl(), false);
		slot.setImageUrlSmall(thumbnail);
		slot.setCreateTime(new Date());
		slot.setLikeCount(0);
		slot.setCommentCount(0);

		String sourceFrom = slot.getSourceFrom();
		SourceFrom sourceFromEnum = SourceFrom.getSourceFromByTypeValue(sourceFrom);
		if (null == sourceFromEnum) {
			sourceFromEnum = SourceFrom.PC;
		}
		slot.setSourceFrom(sourceFromEnum.getType());
		this.spitSlotMapper.insert(slot);
		// userService.changeMark(slot.getUserId(),
		// MarkEnum.MARK_SPIT_SLOT.getMark());

		// 异步发送@消息
		/*
		 * MessageParams messageParams = new MessageParams();
		 * messageParams.setArticleId(slot.getId());
		 * messageParams.setArticleUserId(slot.getUserId());
		 * messageParams.setMessageType(MessageType.AT_ARTICLE_MESSAGE);
		 * messageParams.setArticleType(ArticleType.SPITSLOT);
		 * messageParams.setReceiveUserIds(receiveUserIds);
		 * messageParams.setSendUserName(slot.getUserName());
		 * messageParams.setSendUserId(slot.getUserId());
		 * messageService.createMessage(messageParams);
		 */
	}

	@Override
	public PaginationResult<SpitSlot> findSpitSlotList(SpitSlotQuery query) {
		int count = this.spitSlotMapper.selectCount(query);
		// 每页的第一个序号
		int pageNo = 0;
		// 每页20个
		int pageSize = PageSize.SIZE20.getSize();
		if (null != query.getPageNo()) {
			pageNo = query.getPageNo();
		}
		SimplePage page = new SimplePage(pageNo, count, pageSize);
		query.setPage(page);
		List<SpitSlot> list = this.spitSlotMapper.selectList(query);
		PaginationResult<SpitSlot> result = new PaginationResult<>(page, list);
		return result;
	}

	@Override
	public PaginationResult<SpitSlot> findSpitSlotListNoComment(SpitSlotQuery query) {
		int count = this.spitSlotMapper.selectCount(query);
		int pageSize = PageSize.SIZE20.getSize();
		int pageNo = 0;
		if (null != query.getPageNo()) {
			pageNo = query.getPageNo();
		}
		SimplePage page = new SimplePage(pageNo, count, pageSize);
		query.setPage(page);
		List<SpitSlot> list = this.spitSlotMapper.selectListNoComment(query);
		PaginationResult<SpitSlot> result = new PaginationResult<SpitSlot>(page, list);
		return result;
	}

	@Override
	public SpitSlot findSpitSlot(Integer userId, Integer id) {
		if (null == userId || null == id) {
			return null;
		}
		SpitSlotQuery query = new SpitSlotQuery();
		query.setUserId(userId);
		query.setId(id);
		List<SpitSlot> list = this.spitSlotMapper.selectList(query);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public SpitSlot findSpitSlotById(Integer id) {
		if (null == id || id.equals("")) {
			return null;
		}
		SpitSlotQuery query = new SpitSlotQuery();
		query.setId(id);
		List<SpitSlot> list = this.spitSlotMapper.selectList(query);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public SpitSlot findSpitSlotNoCommentId(Integer id) {
		if (null == id || id.equals("")) {
			return null;
		}
		SpitSlotQuery query = new SpitSlotQuery();
		query.setId(id);
		List<SpitSlot> list = this.spitSlotMapper.selectListNoComment(query);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 事物回滚异常为业务异常
	 * 
	 * @param slot
	 * @throws BusinessException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BusinessException.class)
	public void addSpitSlotComment(SpitSlotComment comment) throws BusinessException {
		String content = comment.getContent();
		if (null == content || null == comment.getUserId() || content.length() > TextLengthEnum.TEXT.getLength()) {
			throw new BusinessException("参数错误");
		}
		// 判断操作阀值
		// thresholdService.canOp(comment.getUserId());
		content = StringTools.addLink(content);
		Set<Integer> receiveUserIds = new HashSet<Integer>();
		String formatContent = formatAtService.generateRefererLinks(content, receiveUserIds, comment.getUserId());
		String sourceFrom = comment.getSourceFrom();
		SourceFrom sourceFromEnum = SourceFrom.getSourceFromByTypeValue(sourceFrom);
		if (null == sourceFromEnum) {
            sourceFromEnum = SourceFrom.PC;
        }
        comment.setSourceFrom(sourceFromEnum.getType());
        
        this.spitSlotCommentMapper.insert(comment);
        //更新吐槽评论数
        this.spitSlotMapper.updateSlotCommentCount(comment.getSpitSlotId(),1);
        //加积分
        userService.changeMark(comment.getUserId(), MarkEnum.MARK_SPIT_SLOT_COMMENT.getMark());
        
        /*MessageParams messageParams = new MessageParams();
        messageParams.setArticleId(comment.getSpitSlotId());
        messageParams.setCommentId(comment.getId());
        messageParams.setMessageType(MessageType.COMMENT_MESSAGE);
        messageParams.setArticleType(ArticleType.SPITSLOT);
        messageParams.setReceiveUserIds(receiveUserIds);
        messageParams.setSendUserName(comment.getUserName());
        messageParams.setSendUserId(comment.getUserId());
        messageService.createMessage(messageParams);*/
	}

	@Override
	public List<SpitSlotComment> loadSpitSlotComment(Integer spitSlotId) {
		SpitSlotQuery query = new SpitSlotQuery();
		query.setSpitSlotId(spitSlotId);
		List<SpitSlotComment> result = this.spitSlotCommentMapper.selectList(query);
		return result;
	}

	@Override
	public List<SpitSlot> findActiveUser4SpitSlot() {
		//是根据谁发的吐槽数最多来排序，吐槽越多越活跃
		SpitSlotQuery query = new SpitSlotQuery();
        query.setPage(new SimplePage(0, PageSize.SIZE20.getSize()));
        return spitSlotMapper.selectActiveUser4SpitSlot(query);
	}

	@Override
	public PaginationResult<SpitSlotComment> findSpitSlotCommentList(SpitSlotQuery query) {
		// 对评论进行分页查询
		int pageNo = 0;
		int count = this.spitSlotCommentMapper.selectCount(query);
		int pageSize = PageSize.SIZE20.getSize();
		if (null != query.getPageNo()) {
            pageNo = query.getPageNo();
        }
		SimplePage page = new SimplePage(pageNo,count,pageSize);
		query.setPage(page);
		List<SpitSlotComment> list = this.spitSlotCommentMapper.selectList(query);
		PaginationResult<SpitSlotComment> result = new PaginationResult<>(page, list);
		return result;
	}
	//删除功能目前只有管理员能使用
	@Override
	public void deleteSpitSlot(Integer spitSlotId) {
		SpitSlotQuery query = new SpitSlotQuery();
        query.setId(spitSlotId);
        spitSlotMapper.delete(query);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteSpitSlotComment(Integer spitSlotId, Integer commentId) {
		SpitSlotQuery query = new SpitSlotQuery();
        query.setId(commentId);
        spitSlotCommentMapper.delete(query);
        spitSlotMapper.updateSlotCommentCount(spitSlotId, -1);
	}

}
