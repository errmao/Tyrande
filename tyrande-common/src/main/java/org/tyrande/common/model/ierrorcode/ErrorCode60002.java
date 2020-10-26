package org.tyrande.common.model.ierrorcode;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * token失效
 *
 * @author you.jiu
 * @date 2020/10/23 16:03
 */
public class ErrorCode60002 implements IErrorCode {

    @Override
    public long getCode() {
        return 60002;
    }

    @Override
    public String getMsg() {
        return "没有权限";
    }
}
