package com.todostudy.todo.controller;

import com.todostudy.cmn.ListResVO;
import com.todostudy.cmn.ObjResVO;
import com.todostudy.todo.service.TodoService;
import com.todostudy.todo.vo.TodoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<ObjResVO<Integer>> registerTodo(@RequestBody TodoVO todoVO) {
        //유저아이디 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userID = (String) authentication.getPrincipal();
        //전달
        return ResponseEntity.ok(todoService.register(todoVO, userID));
    }

    @PostMapping("/list")
    public ResponseEntity<ListResVO<TodoVO>> getTodoList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        return ResponseEntity.ok(todoService.getTodoList(userId));
    }

    @PostMapping("/update")
    public ResponseEntity<ObjResVO<Integer>> updateTodo(@RequestBody TodoVO todoVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(todoService.updateTodo(todoVO, userId));
    }

    @PostMapping("/delete")
    public ResponseEntity<ObjResVO<Integer>> deleteTodo(@RequestBody TodoVO todoVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(todoService.deleteTodo(todoVO, userId));
    }
}
