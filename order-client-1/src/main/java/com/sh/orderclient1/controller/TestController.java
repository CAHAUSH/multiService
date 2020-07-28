package com.sh.orderclient1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String heloo(){
        return "this is a beautiful world from port:"+port;
    }
}
