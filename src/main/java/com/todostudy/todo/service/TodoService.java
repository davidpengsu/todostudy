package com.todostudy.todo.service;

import com.todostudy.cmn.ObjResVO;
import com.todostudy.todo.mapper.TodoMapper;
import com.todostudy.todo.vo.TodoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper todoMapper;

    public ObjResVO<Integer> register(TodoVO todoVO, String userId) {
        todoVO.setUserId(userId);
        //등록날짜 오늘로
        todoVO.setRegDate(LocalDateTime.now().toString());
        //삭제부분 N으로
        todoVO.setDelYn("N");
        todoMapper.savetodo(todoVO);
        return ObjResVO.<Integer>builder()
                .message(1) //성공하면 1 뱉기
                .build();
    }
}
