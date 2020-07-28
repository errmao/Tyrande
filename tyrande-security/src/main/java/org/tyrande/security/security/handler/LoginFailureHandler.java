package org.tyrande.security.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.tyrande.common.constant.NormalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 *
 * @author Tyrande
 */
@Slf4j
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(NormalConstants.JSON_UTF8);
        log.info("[用户登录失败：{}]", exception.getMessage());
        R r = R.failed(exception.getMessage());
        response.getWriter().write(JSONObject.toJSONString(r));
    }
}
