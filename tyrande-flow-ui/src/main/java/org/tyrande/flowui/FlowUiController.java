package org.tyrande.flowui;

import org.flowable.ui.common.model.RemoteUser;
import org.flowable.ui.common.model.UserRepresentation;
import org.flowable.ui.common.security.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyrande.common.model.JwtUser;
import org.tyrande.common.utils.SecurityUserInfoUtil;

/**
 * 流程设计器相关(伪造一些信息)
 *
 * @author Tyrande
 */
@RestController
public class FlowUiController {

    /**
     * 获取当前登陆用户信息
     */
    @GetMapping(value = "/rest/account")
    public UserRepresentation getAccount() {
        JwtUser jwtUser = SecurityUserInfoUtil.getCurrentUser();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName("night");
        userRepresentation.setLastName("tyrande");
        userRepresentation.setFullName(jwtUser.getUsername());
        userRepresentation.setId(jwtUser.getId().toString());

        RemoteUser user = new RemoteUser();
        user.setLastName("tyrande");
        user.setId(jwtUser.getId().toString());
        user.setFirstName("night");
        user.setFullName(jwtUser.getUsername());
        SecurityUtils.assumeUser(user);
        return userRepresentation;
    }

}
