package org.tyrande.common.model.ierrorcode;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * token失效
 *
 * @author you.jiu
 * @date 2020/10/23 16:03
 */
public class ErrorCode60001 implements IErrorCode {

    @Override
    public long getCode() {
        return 60001;
    }

    @Override
    public String getMsg() {
        return "token 失效";
    }
}
