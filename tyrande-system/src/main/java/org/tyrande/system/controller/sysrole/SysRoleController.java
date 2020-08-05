package org.tyrande.system.controller.sysrole;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.validation.annotation.Validated;
import org.tyrande.system.model.sysrole.SysRoleModel;
import org.tyrande.system.vo.sysrole.SysRoleVo;
import org.tyrande.system.service.sysrole.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 系统角色表 Controller 层
 *
 * @author Tyrande
 * @date 2020-08-05
 */
@Validated
@RestController
@RequestMapping("sysrole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 分页查询
     */
    @GetMapping("listPage")
    public R getPageList(SysRoleVo sysRoleVo) {
        return R.ok(sysRoleService.getPageList(sysRoleVo));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("getById")
    public R getById(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        return R.ok(sysRoleService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping("save")
    public R save(@Valid SysRoleModel model) {
        sysRoleService.save(model);
        return R.ok(null);
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public R update(@Valid SysRoleModel model) {
        sysRoleService.updateById(model);
        return R.ok(null);
    }

    /**
     * 删除
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        sysRoleService.removeById(id);
        return R.ok(null);
    }
}