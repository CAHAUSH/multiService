package com.sh.zuulgateway.common;

import lombok.Data;
/*@Data注解，idea需要下载插件：lombok*/
@Data
public class Result<T> {
    private int code = 0;
    private String message = "ok";
    private T data;
}
