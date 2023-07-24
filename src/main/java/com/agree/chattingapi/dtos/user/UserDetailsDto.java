package com.agree.chattingapi.dtos.user;

import com.agree.chattingapi.entities.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsDto implements UserDetails {

    private UserInfo userInfo;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsDto(UserInfo userInfo, Collection<? extends GrantedAuthority> authorities) {
        this.userInfo = userInfo;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return userInfo.getPw();
    }

    @Override
    public String getUsername() {
        return userInfo.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
