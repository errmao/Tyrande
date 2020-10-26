package org.tyrande.starter;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * sd
 *
 * @author you.jiu
 * @date 2020/10/26 9:11
 */
@Slf4j
@RestController
public class TestController {

    @PostMapping("/send/customer")
    public R getInfo(@RequestBody R r) {
        log.info(r.toString());
        return R.ok("发送成功");
    }
}
