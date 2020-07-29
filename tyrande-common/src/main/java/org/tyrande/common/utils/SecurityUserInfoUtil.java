package org.tyrande.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.tyrande.common.exception.customize.CustomException;
import org.tyrande.common.model.JwtUser;

/**
 * spring security 中用户信息获取工具类
 *
 * @author zhaow.zhu
 * @date 2020/5/7 15:39
 */
@Slf4j
public class SecurityUserInfoUtil {

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public static JwtUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
            return jwtUser;
        }
        log.error("[获取当前登录用户出错]");
        throw new CustomException("[获取当前登录用户出错]");
    }
}
