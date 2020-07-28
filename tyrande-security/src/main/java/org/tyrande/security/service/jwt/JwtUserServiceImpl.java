package org.tyrande.security.service.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tyrande.security.dao.jwt.JwtUserDao;
import org.tyrande.security.security.JwtUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tyrande
 */
@Slf4j
@Service
public class JwtUserServiceImpl implements JwtUserService {

    @Resource
    private JwtUserDao jwtUserDao;

    /**
     * 根据登录名加载用户信息
     */
    @Override
    public JwtUser findByUsername(String username) {
        log.info("用户接口查询 ： 根据用户名查询用户");
        JwtUser jwtUser = jwtUserDao.findByUsername(username);
        if (jwtUser == null) {
            throw new UsernameNotFoundException(String.format("没有用户： '%s'.", username));
        }
        // 查询角色
        List<String> roleIdList = jwtUserDao.getRoleList(jwtUser.getUserId());

        List<GrantedAuthority> list = new ArrayList<>();
        for (String roleId : roleIdList) {
            list.add(new SimpleGrantedAuthority(roleId));
        }
        jwtUser.setAuthorities(list);
        return jwtUser;
    }
}
