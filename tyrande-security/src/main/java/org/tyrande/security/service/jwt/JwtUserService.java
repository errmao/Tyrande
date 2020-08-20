package org.tyrande.security.service.jwt;

import org.tyrande.common.model.JwtUser;

/**
 * jwt 查询用户
 *
 * @author Tyrande
 */
public interface JwtUserService {

    /**
     * 根据登录名加载用户信息
     */
    JwtUser loadUserByUsername(String username);

}
