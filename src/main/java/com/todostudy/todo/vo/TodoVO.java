package com.todostudy.todo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoVO {
    private Integer idx;
    private String userId;
    private String content;
    private String regDate;
    private String expDate;
    private String delYn;
}
