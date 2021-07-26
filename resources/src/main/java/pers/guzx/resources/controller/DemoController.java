package pers.guzx.resources.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/7/23 14:48
 * @describe
 */
@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

    @GetMapping("/query")
    public String query() {
        return "query";
    }
}
