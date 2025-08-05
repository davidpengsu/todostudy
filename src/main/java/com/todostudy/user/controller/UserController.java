package com.todostudy.user.controller;

import com.todostudy.user.dto.UserJoinRequestDto;
import com.todostudy.user.dto.UserLoginRequestDto;
import com.todostudy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //가입
    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody UserJoinRequestDto requestDto) {
        userService.join(requestDto);
        return ResponseEntity.ok().build();
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto requestDto) {
        String token = userService.login(requestDto);
        return ResponseEntity.ok(token);
    }
}
