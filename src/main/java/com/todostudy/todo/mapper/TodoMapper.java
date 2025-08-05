package com.todostudy.todo.mapper;

import com.todostudy.todo.domain.Todo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {
    void insertTodo(Todo todo);
}
