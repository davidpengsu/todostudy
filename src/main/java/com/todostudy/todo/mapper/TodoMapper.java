package com.todostudy.todo.mapper;

import com.todostudy.todo.vo.TodoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {
    void savetodo(TodoVO todoVO);
}
