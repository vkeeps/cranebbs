package com.crane.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpitSlotMapper<SpitSlot, SpitSlotQuery> extends BaseMapper<SpitSlot, SpitSlotQuery> {

    public void updateSlotCommentCount(@Param("id") Integer id, @Param("count") Integer count);

    public void updateSlotLikeCount(Integer id);

    public List<SpitSlot> selectActiveUser4SpitSlot(SpitSlotQuery query);

    public List<SpitSlot> selectListNoComment(SpitSlotQuery query);

}