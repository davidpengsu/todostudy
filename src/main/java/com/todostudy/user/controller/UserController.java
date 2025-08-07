package com.todostudy.user.controller;

import com.todostudy.cmn.ObjResVO;
import com.todostudy.user.service.UserService;
import com.todostudy.user.vo.TokenResVO;
import com.todostudy.user.vo.UserVO;
import com.todostudy.user.vo.ValidationGroup;
import jakarta.validation.Valid;
import com.todostudy.user.vo.ValidationGroup;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<ObjResVO<String>> join(@RequestBody @Validated({ValidationGroup.Join.class, Default.class}) UserVO userVO) {
        return ResponseEntity.ok(userService.join(userVO));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<TokenResVO> login(@RequestBody @Validated({ValidationGroup.Login.class, Default.class}) UserVO userVO) {
        TokenResVO entity = userService.login(userVO);
        return ResponseEntity.ok(entity);
    }
}
