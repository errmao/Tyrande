package org.tyrande.security.security;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tyrande.common.constant.NormalConstants;
import org.tyrande.common.model.JwtUser;
import org.tyrande.common.model.ierrorcode.ErrorCode60001;
import org.tyrande.common.model.ierrorcode.ErrorCode60002;
import org.tyrande.common.utils.SpringContextUtil;
import org.tyrande.security.common.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 拦截请求进行token验证(实现token校验的核心)
 *
 * @author Tyrande
 */
@Slf4j
public class JwtHeadFilter extends OncePerRequestFilter {

    /**
     * 可以直接访问通过的请求
     */
    private String[] allowRequest = {"/tyrande/send/customer"};

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
        if (Arrays.asList(allowRequest).contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setContentType(NormalConstants.JSON_UTF8);
            R r = R.failed(new ErrorCode60002());
            response.getWriter().write(String.valueOf(r.getCode()));
            return;
        }
        RedisTemplate redisTemplate = (RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        Object redisTokenObj = redisTemplate.opsForValue().get(token);

        // 过期
        if (StringUtils.isEmpty(redisTokenObj)) {
            response.setContentType(NormalConstants.JSON_UTF8);
            R r = R.failed(new ErrorCode60001());
            response.getWriter().write(String.valueOf(r.getCode()));
            return;
        }
        String redisToken = redisTokenObj.toString();
        String subject = JwtTokenUtil.getProperties(redisToken);
        JwtUser user = JSONObject.parseObject(subject, JwtUser.class);
        // 调用方法，刷新token
        String salt = "token";
        String resultToken = DigestUtils.md5DigestAsHex((salt + user.getId()).getBytes());
        redisTemplate.expire(resultToken, 1800L, TimeUnit.SECONDS);


        JwtLoginToken jwtLoginToken = new JwtLoginToken(user, "", user.getAuthorities());
        jwtLoginToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(jwtLoginToken);
        filterChain.doFilter(request, response);
    }

}
