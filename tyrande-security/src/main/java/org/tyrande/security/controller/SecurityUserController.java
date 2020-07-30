package org.tyrande.security.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyrande.security.common.JwtTokenUtil;
import org.tyrande.security.service.menu.InitMenuService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录后用户信息查询
 *
 * @author Tyrande
 */
@Validated
@RestController
public class SecurityUserController {

    @Value("${tyrande.defaultAvatar}")
    private String defaultAvatar;

    @Resource
    private InitMenuService initMenuService;
    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("info")
    public R getUserInfo(@NotNull(message = "token 参数不能为空") String token) {
        String userInfo = JwtTokenUtil.getProperties(token);
        JSONObject object = JSON.parseObject(userInfo);
        String name = object.getString("username");
        JSONArray roleArray = object.getJSONArray("authorities");
        String avatar = object.getString("avatar");
        List<String> roles = new ArrayList<>();
        for (int i = 0; i < roleArray.size(); i++) {
            JSONObject jsonObject = roleArray.getJSONObject(i);
            roles.add(jsonObject.getString("authority"));
        }
        Map<String, Object> map = new HashMap<>(6);
        map.put("name", name);
        if (StringUtils.isEmpty(avatar)) {
            avatar = defaultAvatar;
        }
        map.put("avatar", avatar);
        map.put("roles", roles);
        map.put("menus", initMenuService.getMenuTreeByUser());
        return R.ok(map);
    }
}
