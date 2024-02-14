package com.success.bigevent.service.cate;

import com.success.bigevent.DTO.EventResp;
import com.success.bigevent.model.EventBusinessDO;

import java.util.List;
import java.util.Map;

public interface EventBusinessService {

    List<EventResp> listAll(Map<String,Object> map);
}
