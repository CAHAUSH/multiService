package com.sh.orderclient1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;

    @PostMapping("/hello")
    public String heloo(@RequestBody String jsonStr){
        return "this is a beautiful world from port:"+port+",name is "+jsonStr;
    }

    @RequestMapping("/test")
    public String test(){
        return "this is a beautiful world from port:"+port;
    }
}
