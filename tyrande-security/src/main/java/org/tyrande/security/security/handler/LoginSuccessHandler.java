package org.tyrande.security.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;
import org.tyrande.common.constant.NormalConstants;
import org.tyrande.common.utils.SpringContextUtil;
import org.tyrande.security.common.JwtTokenUtil;
import org.tyrande.security.security.JwtUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 登录成功处理器
 *
 * @author Tyrande
 */
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType(NormalConstants.JSON_UTF8);
        String userJsonStr = JSON.toJSONString(authentication.getPrincipal());
        String token = JwtTokenUtil.createToken("", userJsonStr, 1800L);

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        String resultToken = DigestUtils.md5DigestAsHex((NormalConstants.JWT_SALT + jwtUser.getId()).getBytes());

        RedisTemplate redisTemplate = (RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        redisTemplate.opsForValue().set(resultToken, token, 1800L, TimeUnit.SECONDS);
        //签发token
        R r = R.ok(resultToken);
        response.getWriter().write(JSONObject.toJSONString(r));
    }
}