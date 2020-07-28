package org.tyrande.security.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.tyrande.security.security.JwtUser;

import javax.annotation.Resource;

/**
 * JWT userService
 *
 * @author Tyrande
 */
@Component
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Resource
    private JwtUserService jwtUserService;

    /**
     * 根据用户登录名定位用户
     *
     * @param username 登录名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUser jwtUser = jwtUserService.findByUsername(username);
        if (jwtUser == null) {
            throw new UsernameNotFoundException("该用户不存在！");
        }
        return jwtUser;
    }
}
