package org.tyrande.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author Tyrande
 */
@ComponentScan(basePackages = {"org.tyrande.*"})
@SpringBootApplication
public class TyrandeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TyrandeApplication.class, args);
    }
}
