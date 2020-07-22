package org.tyrande.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * s
 *
 * @author Tyrande
 */
@RestController
@RequestMapping(("test"))
public class TestController {

    @GetMapping("ttt2")
    public String gettt() {
        return "sssssss";
    }
}
