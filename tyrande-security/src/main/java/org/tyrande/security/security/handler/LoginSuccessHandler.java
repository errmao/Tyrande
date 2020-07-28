package org.tyrande.security.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.tyrande.common.constant.NormalConstants;
import org.tyrande.common.utils.SpringContextUtil;
import org.tyrande.security.common.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 登录成功处理器
 *
 * @author Tyrande
 */
@Slf4j
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType(NormalConstants.JSON_UTF8);
        String userJsonStr = JSON.toJSONString(authentication.getPrincipal());
        String token = JwtTokenUtil.createToken("", userJsonStr, 1800L);

        RedisTemplate redisTemplate = (RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        redisTemplate.opsForValue().set(token, token, 1800L, TimeUnit.SECONDS);
        log.info("[用户登录成功]");
        //签发token
        R r = R.ok(token);
        response.getWriter().write(JSONObject.toJSONString(r));
    }
}