package org.tyrande.security.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 配置路径访问限制
 *
 * @author Tyrande
 */
@Component
@Slf4j
public class AccessDecisionService {

    public boolean hasPermission(HttpServletRequest request, Authentication auth) {
        // 有角色就能访问
        return !auth.getAuthorities().isEmpty();
    }
}
