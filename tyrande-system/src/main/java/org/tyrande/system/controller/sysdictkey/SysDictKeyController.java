package org.tyrande.system.controller.sysdictkey;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.validation.annotation.Validated;
import org.tyrande.system.model.sysdictkey.SysDictKeyModel;
import org.tyrande.system.vo.sysdictkey.SysDictKeyVo;
import org.tyrande.system.service.sysdictkey.SysDictKeyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 系统字典项表 Controller 层
 *
 * @author Tyrande
 * @date 2020-08-04
 */
@Validated
@RestController
@RequestMapping("sysdictkey")
public class SysDictKeyController {

    @Resource
    private SysDictKeyService sysDictKeyService;

    /**
     * 分页查询
     */
    @GetMapping("listPage")
    public R getPageList(SysDictKeyVo sysDictKeyVo) {
        return R.ok(sysDictKeyService.getPageList(sysDictKeyVo));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("getById")
    public R getById(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        return R.ok(sysDictKeyService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping("save")
    public R save(@Valid SysDictKeyModel model) {
        sysDictKeyService.save(model);
        return R.ok(null);
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public R update(@Valid SysDictKeyModel model) {
        sysDictKeyService.updateById(model);
        return R.ok(null);
    }

    /**
     * 删除
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        sysDictKeyService.removeById(id);
        return R.ok(null);
    }
}