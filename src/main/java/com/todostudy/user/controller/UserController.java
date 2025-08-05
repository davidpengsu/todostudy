package com.todostudy.user.controller;

import com.todostudy.cmn.ObjResVO;
import com.todostudy.user.service.UserService;
import com.todostudy.user.vo.TokenResVO;
import com.todostudy.user.vo.UserVO;
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
    public ResponseEntity<ObjResVO<Integer>> join(@RequestBody UserVO userVO) {
        return ResponseEntity.ok(userService.join(userVO));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<TokenResVO> login(@RequestBody UserVO userVO) {
        TokenResVO entity = userService.login(userVO);
        return ResponseEntity.ok(entity);
    }
}
