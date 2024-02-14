package com.success.bigevent.config.security;

import com.success.bigevent.model.UserDO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class LoginUser implements UserDetails {

    private UserDO user;

    private List<String> permissions;

    public LoginUser() {
        super();
    }

    public LoginUser(UserDO user,List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (!Objects.isNull(getAuthorities())) {
//            return getAuthorities();
//        }
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority
        //第一种方式
//         List<GrantedAuthority> newList = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            newList.add(authority);
//        }

        //方式二
        return this.permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
