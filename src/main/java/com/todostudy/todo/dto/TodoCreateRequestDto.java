package com.todostudy.todo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreateRequestDto {
    private String content;
    private String expDate; //만료일 받을때 yyyymmdd형식으로받기!!!
}
