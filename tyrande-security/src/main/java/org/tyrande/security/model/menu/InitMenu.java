package org.tyrande.security.model.menu;

import lombok.Data;

import java.util.Date;

/**
 * 菜单树节点
 *
 * @author Tyrande
 */
@Data
public class InitMenu {

    private Integer id;

    private String menuName;

    private Integer menuLevel;

    private Integer pid;

    private String menuUrl;

    private Date createTime;

    private Date updateTime;
}