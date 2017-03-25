package com.crane.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crane.po.model.SpitSlotComment;

@Repository
public interface SpitSlotCommentMapper<T, Q> extends BaseMapper<T, Q> {
	public List<SpitSlotComment> selectListBySpitSlotId(@Param("spitSlotId") Integer spitSlotId);
}