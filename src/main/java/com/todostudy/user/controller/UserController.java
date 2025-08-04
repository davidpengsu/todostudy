package com.todostudy.user.controller;

import com.todostudy.user.dto.UserJoinRequestDto;
import com.todostudy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(UserJoinRequestDto requestDto) {
        userService.join(requestDto);
        return ResponseEntity.ok().build();
    }
}
