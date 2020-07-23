package org.tyrande.generator.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tyrande
 */
@RestController
public class Test {

    @GetMapping("test")
    public R dd() {
        return R.ok("ssss");
    }
}
