package com.success.bigevent.config.security;

import com.alibaba.fastjson.JSONObject;
import com.success.bigevent.DTO.Result;
import com.success.bigevent.common.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<Object> result = new Result<>(HttpStatus.FORBIDDEN.value(), "权限不足",null,false);
        String json = JSONObject
                .toJSONString(result);
        WebUtils.renderString(response, json);
    }

}
