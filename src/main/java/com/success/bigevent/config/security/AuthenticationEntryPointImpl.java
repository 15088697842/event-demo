package com.success.bigevent.config.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.success.bigevent.DTO.Result;
import com.success.bigevent.common.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        String message = "";
        if(authException instanceof BadCredentialsException){
            message = "用户账号或者密码错误 ";
        }
        if(authException instanceof InsufficientAuthenticationException){
            message = "账号未登陆";
        }
        Result<Object> result = new Result<>(HttpStatus.UNAUTHORIZED.value(), message, null,false);
        String json = JSONObject.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
