package com.success.bigevent.mapper;

import com.success.bigevent.model.EventBusinessDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface BusinessMapper {

    List<EventBusinessDO> selectList(Map<String,Object> map);
}
