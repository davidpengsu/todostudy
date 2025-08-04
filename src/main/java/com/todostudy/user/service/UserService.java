package com.todostudy.user.service;

import com.todostudy.user.domain.User;
import com.todostudy.user.dto.UserJoinRequestDto;
import com.todostudy.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public void join (UserJoinRequestDto requestDto) {
        //todo: 회원가입로직구현
        User user = User.builder()
                .userId(requestDto.getUserId())
                .userName(requestDto.getUserName())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role("ROLE_USER")
                .delYn("N")
                .regDate(LocalDateTime.now().toString())
                .lastLgnTime(LocalDateTime.now().toString())
                .build();
        userMapper.save(user);

    }
}
