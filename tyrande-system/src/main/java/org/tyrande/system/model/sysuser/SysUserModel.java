package org.tyrande.system.model.sysuser;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表对象 sys_user
 *
 * @author Tyrande
 * @date 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登陆账户
     */
    private String loginCode;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 性别 1-男 2-女
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱是否验证 0-否 1-是
     */
    private Integer validEmail;
    /**
     * 电话
     */
    private String tel;
    /**
     * 电话是否验证 0-否 1-是
     */
    private Integer validTel;
    /**
     * 用户状态 0-未使用 1-正常 2-锁定 3-过期
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}