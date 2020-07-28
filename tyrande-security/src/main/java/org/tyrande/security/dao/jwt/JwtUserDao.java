package org.tyrande.security.dao.jwt;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tyrande.security.security.JwtUser;

import java.util.List;

/**
 * 查询用户实体
 *
 * @author Tyrande
 */
@Mapper
public interface JwtUserDao {

    /**
     * 根据登录账户查询用户信息
     *
     * @param username 登录账户
     * @return
     */
    JwtUser findByUsername(@Param("username") String username);

    /**
     * 根据用户编号查询用户拥有角色编号
     *
     * @param userId 用户编号
     * @return
     */
    List<String> getRoleList(@Param("userId") Integer userId);
}
