package com.todostudy.todo.controller;

import com.todostudy.todo.domain.Todo;
import com.todostudy.todo.dto.TodoCreateRequestDto;
import com.todostudy.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoCreateRequestDto requestDto) {
        //todo: jwt토큰에서 유저아이디 가져오는거 구현하기
        String userId = "testuser";
        Todo createdTodo = todoService.createTodo(userId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }
}
