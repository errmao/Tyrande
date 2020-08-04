package org.tyrande.system.controller.sysdictvalue;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.validation.annotation.Validated;
import org.tyrande.system.model.sysdictvalue.SysDictValueModel;
import org.tyrande.system.vo.sysdictvalue.SysDictValueVo;
import org.tyrande.system.service.sysdictvalue.SysDictValueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 系统字典值表 Controller 层
 *
 * @author Tyrande
 * @date 2020-08-04
 */
@Validated
@RestController
@RequestMapping("sysdictvalue")
public class SysDictValueController {

    @Resource
    private SysDictValueService sysDictValueService;

    /**
     * 分页查询
     */
    @GetMapping("listPage")
    public R getPageList(SysDictValueVo sysDictValueVo) {
        return R.ok(sysDictValueService.getPageList(sysDictValueVo));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("getById")
    public R getById(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        return R.ok(sysDictValueService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping("save")
    public R save(@Valid SysDictValueModel model) {
        sysDictValueService.save(model);
        return R.ok(null);
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public R update(@Valid SysDictValueModel model) {
        sysDictValueService.updateById(model);
        return R.ok(null);
    }

    /**
     * 删除
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        sysDictValueService.removeById(id);
        return R.ok(null);
    }
}