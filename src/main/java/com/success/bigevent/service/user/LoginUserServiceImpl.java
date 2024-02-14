package com.success.bigevent.service.user;

import com.success.bigevent.config.security.LoginUser;
import com.success.bigevent.mapper.UserMapper;
import com.success.bigevent.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userMapper.selectByName(username);
        if(userDO == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new LoginUser(userDO, List.of("1","2"));
    }
}
