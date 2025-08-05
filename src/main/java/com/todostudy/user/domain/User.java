package com.todostudy.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Getter
public class User implements UserDetails {
    private Long id;
    private String userId;
    private String userName;
    private String password;
    private String regDate;
    private String lastLgnTime;
    private String role;
    private String delYn;

    @Builder
    public User(String userId, String userName, String password, String regDate, String lastLgnTime, String role, String delYn) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.regDate = regDate;
        this.lastLgnTime = lastLgnTime;
        this.role = role;
        this.delYn = delYn;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return this.userId;
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
        return this.delYn.equals("N");
    }

}
