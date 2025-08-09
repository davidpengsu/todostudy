package com.todostudy.todo.controller;

import com.todostudy.cmn.ListResVO;
import com.todostudy.cmn.ObjResVO;
import com.todostudy.todo.service.TodoService;
import com.todostudy.todo.vo.TodoVO;
import com.todostudy.todo.vo.ValidationGroup;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/newtodo")
    public ResponseEntity<ObjResVO<Integer>> registerTodo(@RequestBody @Validated ({ValidationGroup.NewTodo.class, Default.class}) TodoVO todoVO) {
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
    public ResponseEntity<ObjResVO<String>> updateTodo(@RequestBody @Validated ({ValidationGroup.UpdateTodo.class, Default.class}) TodoVO todoVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        ObjResVO<Integer> serviceResult = todoService.updateTodo(todoVO, userId);
        if (serviceResult.getMessage() == 1){
            return ResponseEntity.ok(ObjResVO.<String>builder().message("투두수정이됨").build());
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ObjResVO.<String>builder().message("투두수정안된거임").build());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ObjResVO<String>> deleteTodo(@RequestBody TodoVO todoVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        ObjResVO<Integer> serviceResult = todoService.deleteTodo(todoVO, userId);
        if (serviceResult.getMessage() == 1){
            return ResponseEntity.ok(ObjResVO.<String>builder().message("투두삭제").build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ObjResVO.<String>builder().message("투두삭제안된거임").build());
        }

    }
}
