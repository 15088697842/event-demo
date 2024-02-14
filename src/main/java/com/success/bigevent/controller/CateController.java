package com.success.bigevent.controller;

import com.success.bigevent.DTO.EventResp;
import com.success.bigevent.DTO.Result;
import com.success.bigevent.DTO.UserReq;
import com.success.bigevent.model.EventBusinessDO;
import com.success.bigevent.service.cate.EventBusinessService;
import com.success.bigevent.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("cate")
public class CateController {

    @Autowired
    private EventBusinessService eventBusinessService;

    @GetMapping("list")
//    @CrossOrigin
    public Result<?> cateList()  {
        List<EventResp> eventBusinessDOS = eventBusinessService.listAll(new HashMap<>());
        return Result.success(eventBusinessDOS);
    }

}
