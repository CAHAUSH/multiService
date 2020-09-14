package com.sh.zuulgateway.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sh.zuulgateway.common.Result;
import com.sh.zuulgateway.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/*实现参考https://www.cnblogs.com/lookup/p/zuul-jwt.html*/
public class jwtAuthFilter extends ZuulFilter {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ObjectMapper objectMapper;
    /**
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("token");
        Claims claims;
        try {
            //解析没有异常，表示验证通过
            claims=jwtUtil.parseJWT(token);
            //对请求进行路由
            ctx.setSendZuulResponse(true);
            ctx.addZuulRequestHeader("userId",claims.get("userId").toString());

        }catch (ExpiredJwtException e){
            ctx.setSendZuulResponse(false);
        }


        HttpServletResponse response = ctx.getResponse();
        //添加请求头，传递到业务服务
        ctx.addZuulRequestHeader("xxx", "xxx");
        //添加响应头，返回给前端
        ctx.addZuulResponseHeader("xxx", "xxx");
        return null;
    }

    /**
     * 将异常信息响应给前端
     */
    private void responseError(RequestContext ctx, int code, String message) {
        HttpServletResponse response = ctx.getResponse();
        Result errResult = new Result();
        //errResult.
        ctx.setResponseBody(toJsonString(errResult));
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=utf-8");
    }

    private String toJsonString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
