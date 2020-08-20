package org.tyrande.system.controller.sysuser;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.validation.annotation.Validated;
import org.tyrande.system.model.sysuser.SysUserModel;
import org.tyrande.system.model.sysuser.SysUserRole;
import org.tyrande.system.vo.sysuser.SysUserVo;
import org.tyrande.system.service.sysuser.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 系统用户表 Controller 层
 *
 * @author Tyrande
 * @date 2020-08-03
 */
@Validated
@RestController
@RequestMapping("sysuser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 分页查询
     */
    @GetMapping("listPage")
    public R getPageList(SysUserVo sysUserVo) {
        return R.ok(sysUserService.getPageList(sysUserVo));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("getById")
    public R getById(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        return R.ok(sysUserService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping("save")
    public R save(@Valid SysUserModel model) {
        sysUserService.save(model);
        return R.ok(null);
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public R update(@Valid SysUserModel model) {
        sysUserService.updateById(model);
        return R.ok(null);
    }

    /**
     * 删除
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        sysUserService.removeById(id);
        return R.ok(null);
    }

    /**
     * 查询用户角色信息
     */
    @GetMapping("getUserRoleInfo")
    public R getUserRoleInfo(@RequestParam @NotBlank(message = "用户编号不能为空") String id) {
        return R.ok(sysUserService.getUserRoleInfo(id));
    }

    /**
     * 保存用户角色
     */
    @PostMapping("doConfigSave")
    public R doConfigSave(@Valid SysUserRole role) {
        sysUserService.doConfigSave(role);
        return R.ok(null);
    }
}