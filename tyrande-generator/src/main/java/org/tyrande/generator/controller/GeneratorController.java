package org.tyrande.generator.controller;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyrande.generator.service.GeneratorService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 代码生成控制器
 *
 * @author Tyrande
 */
@Slf4j
@Validated
@RestController
@RequestMapping("generator")
public class GeneratorController {

    @Resource
    private GeneratorService generatorService;

    /**
     * 根据表名生成代码
     *
     * @param tableName  表名
     * @param moduleName 模块名
     * @return
     */
    @GetMapping("generatorCode")
    public R generatorCode(@NotNull(message = "表名不能为空") String tableName,
                           @NotNull(message = "模块不能为空") String moduleName) {
        generatorService.generatorCode(tableName, moduleName);
        return R.ok("代码生成成功");
    }
}
