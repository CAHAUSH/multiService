package com.sh.orderclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
