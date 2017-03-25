package com.crane.mapper;

import org.springframework.stereotype.Repository;

/**
 * 签到
 * @author luo.hl
 * @version 3.0
 */
@Repository("signInMapper")
public interface SignInMapper<T, Q> extends BaseMapper<T, Q> {
}
