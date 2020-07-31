package org.tyrande.system.controller.sysparams;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tyrande.system.model.sysparams.SysParamsModel;
import org.tyrande.system.service.sysparams.SysParamsService;
import org.tyrande.system.vo.sysparams.SysParamsVo;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 系统参数表 Controller 层
 *
 * @author Tyrande
 * @date 2020-07-31
 */
@Validated
@RestController
@RequestMapping("sysparams")
public class SysParamsController {

    @Resource
    private SysParamsService sysParamsService;

    /**
     * 分页查询
     */
    @GetMapping("listPage")
    public R getPageList(SysParamsVo sysParamsVo) {
        return R.ok(sysParamsService.getPageList(sysParamsVo));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("getById")
    public R getById(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        return R.ok(sysParamsService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping("save")
    public R save(@Valid SysParamsModel model) {
        sysParamsService.save(model);
        return R.ok(null);
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public R update(@Valid SysParamsModel model) {
        sysParamsService.updateById(model);
        return R.ok(null);
    }

    /**
     * 删除
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        sysParamsService.removeById(id);
        return R.ok(null);
    }
}