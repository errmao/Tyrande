package org.tyrande.system.controller.sysmenu;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.validation.annotation.Validated;
import org.tyrande.system.model.sysmenu.SysMenuModel;
import org.tyrande.system.vo.sysmenu.SysMenuVo;
import org.tyrande.system.service.sysmenu.SysMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 系统菜单表 Controller 层
 *
 * @author Tyrande
 * @date 2020-07-29
 */
@Validated
@RestController
@RequestMapping("sysmenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 分页查询
     */
    @GetMapping("listPage")
    public R getPageList(SysMenuVo sysMenuVo) {
        return R.ok(sysMenuService.getPageList(sysMenuVo));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("getById")
    public R getById(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        return R.ok(sysMenuService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping("save")
    public R save(@Valid SysMenuModel model) {
        sysMenuService.save(model);
        return R.ok(null);
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public R update(@Valid SysMenuModel model) {
        sysMenuService.updateById(model);
        return R.ok(null);
    }

    /**
     * 删除
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        sysMenuService.removeById(id);
        return R.ok(null);
    }

    /**
     * 根据用户权限获取菜单列表
     */
    @GetMapping("/getMenuByUser")
    public R getMenuByUser() {
        return R.ok(sysMenuService.getMenuTreeByUser());
    }
}