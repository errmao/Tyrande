package org.tyrande.generator.controller;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyrande.generator.service.GeneratorService;

import javax.annotation.Resource;

/**
 * 代码生成控制器
 *
 * @author Tyrande
 */
@Slf4j
@RestController
@RequestMapping("generator")
public class GeneratorController {

    @Resource
    private GeneratorService generatorService;

    @GetMapping("generatorCode")
    public R generatorCode(String tableName) {
        log.info("sssssss");
        generatorService.generatorCode(tableName);
        return R.ok("代码生成成功");
    }
}
