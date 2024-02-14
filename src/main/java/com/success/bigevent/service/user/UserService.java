package com.success.bigevent.service.user;

import com.success.bigevent.DTO.Result;
import com.success.bigevent.DTO.UserReq;
import com.success.bigevent.DTO.UserResp;
import com.success.bigevent.model.EventBusinessDO;
import com.success.bigevent.model.UserDO;

import java.util.List;
import java.util.Map;

public interface UserService {


    Result<?> login(UserReq userReq);


    Result<?> logout();

}
