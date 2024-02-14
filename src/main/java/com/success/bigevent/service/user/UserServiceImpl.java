package com.success.bigevent.service.user;

import com.alibaba.fastjson.JSONObject;
import com.success.bigevent.DTO.Result;
import com.success.bigevent.DTO.UserReq;
import com.success.bigevent.common.utils.JwtUtil;
import com.success.bigevent.common.utils.RedisCache;
import com.success.bigevent.config.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Result<?> login(UserReq userReq) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userReq.getUserName(), userReq.getPassword());

        // AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 获取当前用户的userid
        String userId = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        // 把完整的用户信息存入redis userid为key 用户信息为value
        redisTemplate.opsForValue().set("login:" + userId, JSONObject.toJSON(loginUser), 1200, TimeUnit.SECONDS);

        return new Result<>(200,"登陆成功",map,true);
    }

    @Override
    public Result<?> logout() {
        // 这里为什么能直接拿到？不用传过来？
        //从SecurityContextHolder中的userid
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userid = loginUser.getUser().getId();

        //根据userid找到redis对应值进行删除
        redisTemplate.opsForValue().set("login:" + userid,null);
        return new Result<>(200, "注销成功",null,true);

    }

}
