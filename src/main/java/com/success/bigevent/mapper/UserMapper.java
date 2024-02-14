package com.success.bigevent.mapper;

import com.success.bigevent.model.EventBusinessDO;
import com.success.bigevent.model.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    UserDO  selectByName(String name);
}
