package com.todostudy.user.service;

import com.todostudy.user.domain.User;
import com.todostudy.user.dto.UserJoinRequestDto;
import com.todostudy.user.dto.UserLoginRequestDto;
import com.todostudy.user.mapper.LoginLogMapper;
import com.todostudy.user.mapper.UserMapper;
import com.todostudy.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//로그찍기위함
import com.todostudy.user.dto.LoginLogDto;
import com.todostudy.user.mapper.LoginLogMapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final LoginLogMapper loginLogMapper;

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

    //로그인
    public String login(UserLoginRequestDto requestDto) {
        User user = userMapper.findByUserId(requestDto.getUserId());
        String loginIP = "127.0.0.1"; //todo 실제 아이피 수집하기

        if (user == null) {
            insertLoginLog(requestDto.getUserId(), loginIP, "FAIL");
            throw new IllegalArgumentException("아이디가 존재안함");
        }
        //비번
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            insertLoginLog(requestDto.getUserId(), loginIP, "FAIL");
            throw new IllegalArgumentException("비번틀림");
        }
        //토큰던지기
        insertLoginLog(requestDto.getUserId(), loginIP, "success");
        return jwtUtil.generateToken(user.getUserId());
    }

    private void insertLoginLog(String userId, String ip, String stats) {
        LoginLogDto logDto = LoginLogDto.builder()
                .userId(userId)
                .lgnIpAddr(ip)
                .stats(stats)
                .regDate(LocalDateTime.now())
                .build();
        loginLogMapper.insertLog(logDto);
    }

}

