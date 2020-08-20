package ${packageName}.controller.${classNameLower};

import com.baomidou.mybatisplus.extension.api.R;
import ${packageName}.model.${classNameLower}.${className}Model;
import ${packageName}.vo.${classNameLower}.${className}Vo;
import ${packageName}.service.${classNameLower}.${className}Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * ${tableNameRemark} Controller 层
 *
 * @author ${author}
 * @date ${datetime}
 */
@Validated
@RestController
@RequestMapping("${classNameLower}")
public class ${className}Controller {

    @Resource
    private ${className}Service ${classFirstNameLower}Service;

    /**
     * 分页查询
     */
    @GetMapping("listPage")
    public R getPageList(${className}Vo ${classFirstNameLower}Vo) {
        return R.ok(${classFirstNameLower}Service.getPageList(${classFirstNameLower}Vo));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("getById")
    public R getById(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        return R.ok(${classFirstNameLower}Service.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping("save")
    public R save(@Valid ${className}Model model) {
        ${classFirstNameLower}Service.save(model);
        return R.ok(null);
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public R update(@Valid ${className}Model model) {
        ${classFirstNameLower}Service.updateById(model);
        return R.ok(null);
    }

    /**
     * 删除
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam @NotBlank(message = "主键ID不能为空") String id) {
        ${classFirstNameLower}Service.removeById(id);
        return R.ok(null);
    }
}