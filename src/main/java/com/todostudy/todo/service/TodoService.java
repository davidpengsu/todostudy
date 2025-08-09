package com.todostudy.todo.service;

import com.todostudy.children.vo.ChildrenVO;
import com.todostudy.cmn.ListResVO;
import com.todostudy.cmn.ObjResVO;
import com.todostudy.todo.mapper.TodoMapper;
import com.todostudy.todo.vo.TodoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public ListResVO<TodoVO> getTodoList(String userId) {
        List<TodoVO> todoList = todoMapper.findByUserId(userId);

        return ListResVO.<TodoVO>builder()
                .size(todoList.size())
                .datas(todoList)
                .build();
    }

    public ObjResVO<Integer> updateTodo(TodoVO todoVO, String userId) {
        todoVO.setUserId(userId);

        int updatedCount = todoMapper.updateTodo(todoVO);
        if (updatedCount == 0){
            return ObjResVO.<Integer>builder()
                    .message(0)
                    .build();
        }
        return ObjResVO.<Integer>builder()
                .message(1)
                .build();

    }


    public ObjResVO<Integer> deleteTodo(TodoVO todoVO, String userId) {
        todoVO.setUserId(userId);
        todoVO.setDelYn("Y");

        int deletedCount = todoMapper.deleteTodo(todoVO);
        if (deletedCount == 0){
            return ObjResVO.<Integer>builder()
                    .message(0)
                    .build();
        }
        return ObjResVO.<Integer>builder()
                .message(1)
                .build();
    }
}
