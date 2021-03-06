package org.tyrande.security.config;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.tyrande.common.constant.NormalConstants;
import org.tyrande.security.model.TyrandeSecurityProperty;
import org.tyrande.security.security.JwtAuthenticationProvider;
import org.tyrande.security.security.JwtHeadFilter;
import org.tyrande.security.security.JwtLoginFilter;
import org.tyrande.security.security.handler.LoginFailureHandler;
import org.tyrande.security.security.handler.LoginSuccessHandler;
import org.tyrande.security.security.handler.TyrandeLogoutSuccessHandler;
import org.tyrande.security.service.jwt.JwtUserDetailServiceImpl;

import javax.annotation.Resource;

/**
 * Spring Security 配置类
 *
 * @author Tyrande
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private TyrandeLogoutSuccessHandler tyrandeLogoutSuccessHandler;

    @Resource
    private TyrandeSecurityProperty tyrandeSecurityProperty;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 允许跨域
        http.cors();
        //登录过滤器
        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(tyrandeSecurityProperty.getLoginUrl());
        jwtLoginFilter.setAuthenticationManager(this.authenticationManagerBean());

        //登录成功和失败的操作
        jwtLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        jwtLoginFilter.setAuthenticationFailureHandler(loginFailureHandler);

        //登录过滤器的授权提供者
        JwtAuthenticationProvider provider = new JwtAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(jwtUserDetailService);

        //JWT校验过滤器
        JwtHeadFilter headFilter = new JwtHeadFilter();
        http
                //身份验证入口,当需要登录却没登录时调用
                //具体为,当抛出AccessDeniedException异常时且当前是匿名用户时调用
                //匿名用户: 当过滤器链走到匿名过滤器(AnonymousAuthenticationFilter)时,
                //会进行判断SecurityContext是否有凭证(Authentication),若前面的过滤器都没有提供凭证,
                //匿名过滤器会给SecurityContext提供一个匿名的凭证(可以理解为用户名和权限为anonymous的Authentication),
                //这也是JwtHeadFilter发现请求头中没有jwtToken不作处理而直接进入下一个过滤器的原因
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.setContentType(NormalConstants.JSON_UTF8);
            R r = R.failed("需要登陆");
            response.getWriter().write(JSONObject.toJSONString(r));
        })

                //拒绝访问处理,当已登录,但权限不足时调用
                //抛出AccessDeniedException异常时且当不是匿名用户时调用
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setContentType(NormalConstants.JSON_UTF8);
                    R r = R.failed("没有权限");
                    response.getWriter().write(JSONObject.toJSONString(r));
                })
                .and()
                .authorizeRequests()

                .anyRequest().access("@accessDecisionService.hasPermission(request , authentication)")
                .and()
                //将授权提供者注册到授权管理器中(AuthenticationManager)
                .authenticationProvider(provider)
                .addFilterAfter(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(headFilter, JwtLoginFilter.class)
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .logout().logoutUrl(tyrandeSecurityProperty.getLogoutUrl()).logoutSuccessHandler(tyrandeLogoutSuccessHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
