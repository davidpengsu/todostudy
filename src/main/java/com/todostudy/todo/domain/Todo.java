package com.todostudy.todo.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Todo {
    private String userId;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime expDate;
    private String delYn;

    @Builder
    public Todo(String userId, String content, LocalDateTime regDate, LocalDateTime expDate, String delYn) {
        this.userId = userId;
        this.content = content;
        this.regDate = regDate;
        this.expDate = expDate;
        this.delYn = delYn;
    }

}
