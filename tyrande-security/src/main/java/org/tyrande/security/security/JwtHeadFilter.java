package org.tyrande.security.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tyrande.common.constant.NormalConstants;
import org.tyrande.common.utils.SpringContextUtil;
import org.tyrande.security.common.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 拦截请求进行token验证(实现token校验的核心)
 *
 * @author Tyrande
 */
public class JwtHeadFilter extends OncePerRequestFilter {

    /**
     * 作用：
     * 获取请求中携带的token
     * 若没有获取到token则return,调交给接下来的过滤器链处理
     * 若有token,但是校验失败,进行校验失败处理
     * 若token校验成功,通过从token中获取的用户信息生成一个凭证(Authentication),并放置到SecurityContext
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setContentType(NormalConstants.JSON_UTF8);
            response.getWriter().write("没有权限");
            return;
        }
        RedisTemplate redisTemplate = (RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        // 过期
        String realToken = redisTemplate.opsForValue().get(token).toString();
        if (StringUtils.isEmpty(realToken)) {
            response.setContentType(NormalConstants.JSON_UTF8);
            response.getWriter().write("token 失效");
            return;
        }
        String subject = JwtTokenUtil.getProperties(realToken);
        JwtUser user = JSONObject.parseObject(subject, JwtUser.class);
        // 调用方法，刷新token
        String resultToken = DigestUtils.md5DigestAsHex((NormalConstants.JWT_SALT + user.getId()).getBytes());
        redisTemplate.expire(resultToken, 1800L, TimeUnit.SECONDS);

        JwtLoginToken jwtLoginToken = new JwtLoginToken(user, "", user.getAuthorities());
        jwtLoginToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(jwtLoginToken);
        filterChain.doFilter(request, response);
    }

}
