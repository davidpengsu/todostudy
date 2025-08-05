package com.todostudy.user.service;

import com.todostudy.user.domain.User;
import com.todostudy.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //유저아이디로 데베에서 조회
        User user = userMapper.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException("존재하지않는사용자임" + userId);
        }
        //유저디테일로 반환
        return user;
    }
}
