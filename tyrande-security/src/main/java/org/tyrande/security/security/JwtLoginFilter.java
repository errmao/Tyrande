package org.tyrande.security.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.tyrande.security.model.TyrandeSecurityProperty;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的登录过滤器:
 * 设置登录的url,请求的方式,其实也就是定义这个过滤器要拦截哪个请求
 * 调用JwtAuthenticationProvider进行登录校验
 * 校验成功调用LoginSuccessHandler,校验失败调用LoginSuccessHandler
 *
 * @author Tyrande
 */
@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtLoginFilter(String url) {
        super(new AntPathRequestMatcher(url, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            log.info("[{} 用户登陆]", userName);
            // 创建未认证的凭证(etAuthenticated(false)),注意此时凭证中的主体principal为用户名
            JwtLoginToken jwtLoginToken = new JwtLoginToken(userName, password);
            // 将认证详情(ip,sessionId)写到凭证
            jwtLoginToken.setDetails(new WebAuthenticationDetails(request));
            // AuthenticationManager获取受支持的AuthenticationProvider(这里也就是JwtAuthenticationProvider),
            // 生成已认证的凭证,此时凭证中的主体为userDetails
            Authentication authenticatedToken = this.getAuthenticationManager().authenticate(jwtLoginToken);
            return authenticatedToken;
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
