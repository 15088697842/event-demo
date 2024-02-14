package com.success.bigevent.service.cate;

import com.success.bigevent.DTO.EventResp;
import com.success.bigevent.common.utils.ListConvertUtil;
import com.success.bigevent.mapper.BusinessMapper;
import com.success.bigevent.model.EventBusinessDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventBusinessServiceImpl implements EventBusinessService{

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<EventResp> listAll(Map<String, Object> map) {
        List<EventBusinessDO> eventBusinessDOS = businessMapper.selectList(map);
        return ListConvertUtil.convertList(eventBusinessDOS,EventResp.class);
    }
}
