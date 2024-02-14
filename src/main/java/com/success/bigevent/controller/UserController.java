package com.success.bigevent.controller;

import com.success.bigevent.DTO.Result;
import com.success.bigevent.DTO.UserReq;
import com.success.bigevent.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
//    @CrossOrigin
    public Result<?> login(@RequestParam String username, @RequestParam String password)  {
        UserReq userReq = new UserReq();
        userReq.setUserName(username);
        userReq.setPassword(password);
      return userService.login(userReq);
    }

    @GetMapping("logout")
    public Result<?> logout()  {
        return userService.logout();
    }
}
