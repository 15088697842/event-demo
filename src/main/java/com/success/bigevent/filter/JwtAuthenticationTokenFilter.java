package com.success.bigevent.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.success.bigevent.common.utils.JwtUtil;
import com.success.bigevent.common.utils.RedisCache;
import com.success.bigevent.config.security.LoginUser;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

//@Order(Ordered.HIGHEST_PRECEDENCE)
//@WebFilter
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        String redisKey = "login:" + userId;
        LoginUser loginUser;
        Object o = redisTemplate.opsForValue().get(redisKey);
        if (null == o) {
            throw new RuntimeException("用户未登录");
        }
        String resStr = o.toString(); // 看这里

        JSONObject res = JSONObject.parseObject(resStr);
        loginUser = res.toJavaObject(LoginUser.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,
                null, loginUser.getAuthorities());
        // 通过token获取登陆信息塞进去， 才能通过后面账号是否登陆的过滤
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 只要调接口，就延长redis时间
        redisTemplate.opsForValue().set("login:" + userId, com.alibaba.fastjson.JSONObject.toJSON(loginUser), 1200, TimeUnit.SECONDS);
        filterChain.doFilter(request, response);
    }
}
