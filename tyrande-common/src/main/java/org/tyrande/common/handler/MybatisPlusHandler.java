package org.tyrande.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis plus 自动设置创建和更新时间
 *
 * @author Tyrande
 */
@Component
public class MybatisPlusHandler implements MetaObjectHandler {

    /**
     * 创建时间字段
     */
    private final String createTime = "createTime";

    /**
     * 更新时间字段
     */
    private final String updateTime = "updateTime";

    /**
     * 插入操作填充操作
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter(createTime)) {
            metaObject.setValue(createTime, new Date());
        }
        if (metaObject.hasSetter(updateTime)) {
            metaObject.setValue(updateTime, new Date());
        }
    }

    /**
     * 更新操作填充策略
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue(updateTime, new Date());
    }
}
