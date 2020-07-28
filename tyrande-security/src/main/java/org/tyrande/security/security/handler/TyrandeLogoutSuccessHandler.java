package org.tyrande.security.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功操作
 *
 * @author Tyrande
 */
@Slf4j
@Component
public class TyrandeLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            log.info("[token 不存在]");
        }
        redisTemplate.delete(token);
        log.info("[用户登出成功]");
    }
}
