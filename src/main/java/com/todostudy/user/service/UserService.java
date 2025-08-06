package com.todostudy.user.service;

import com.todostudy.cmn.ObjResVO;
import com.todostudy.user.mapper.UserMapper;
import com.todostudy.user.vo.TokenResVO;
import com.todostudy.user.vo.UserVO;
import com.todostudy.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ObjResVO<Integer> join (final UserVO userVO) {
        UserVO user = new UserVO();

        user.setUserId(userVO.getUserId());
        user.setName(userVO.getName());
        user.setPassword(passwordEncoder.encode(userVO.getPassword()));
        user.setRole("ROLE_USER");
        user.setDelYn("N");

        user.setRegDate(LocalDateTime.now().toString());
        user.setLastLgnTime(LocalDateTime.now().toString());

        userMapper.save(user);

        return ObjResVO.<Integer>builder()
                .message(1)
                .build();
    }

    //로그인
    public TokenResVO login(final UserVO userVO) {
        UserVO user = userMapper.findByUserId(userVO);

        if (user == null) {

            return TokenResVO.builder()
                    .message("이미 존재 하는 아이디 입니다.")
                    .build();
        }

        if (!passwordEncoder.matches(userVO.getPassword(), user.getPassword())) {
            return TokenResVO.builder()
                    .message("비밀번호 또는 아이디가 잘못되었습니다.")
                    .build();
        }

        return TokenResVO.builder()
                .message("로그인을 성공하였습니다")
                .token(jwtUtil.generateToken(user.getUserId()))
                .build();

    }


}

