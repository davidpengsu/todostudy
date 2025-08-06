package com.todostudy.todo.mapper;

import com.todostudy.todo.vo.TodoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    void savetodo(TodoVO todoVO);
    List<TodoVO> findByUserId(String userId);
    void updateTodo(TodoVO todoVO);
    void deleteTodo(TodoVO todoVO);
}
