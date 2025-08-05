package com.todostudy.todo.service;

import com.todostudy.todo.domain.Todo;
import com.todostudy.todo.dto.TodoCreateRequestDto;
import com.todostudy.todo.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
    private final TodoMapper todoMapper;

    //할거등록
    public Todo createTodo(String userId, TodoCreateRequestDto requestDto) {
        Todo todo = Todo.builder()
                .userId(userId)
                .content(requestDto.getContent())
                .regDate(LocalDateTime.now())
                .expDate(parseExpDate(requestDto.getExpDate())) //yyyymmdd
                .delYn("N")
                .build();
        return todo;
    }

    //yyyymmdd형식 로컬데이트타임으로 바꾸기
    private LocalDateTime parseExpDate(String expDateStr) {
        LocalDate localDate = LocalDate.parse(expDateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return localDate.atStartOfDay();
    }

}
