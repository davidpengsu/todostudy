package com.todostudy.todo.mapper;

import com.todostudy.todo.vo.TodoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    int savetodo(TodoVO todoVO);
    List<TodoVO> findByUserId(String userId);
    int updateTodo(TodoVO todoVO);
    int deleteTodo(TodoVO todoVO);
}
