package com.todostudy.todo.vo;

import com.todostudy.user.vo.ValidationGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoVO {
    private Integer idx;
    private String userId;

    @NotBlank(message = "내용은 필수입력임", groups = {com.todostudy.todo.vo.ValidationGroup.NewTodo.class, com.todostudy.todo.vo.ValidationGroup.UpdateTodo.class})
    private String content;
    private String regDate;

    @NotBlank(message = "마감일자는 필수입력임", groups = {com.todostudy.todo.vo.ValidationGroup.NewTodo.class, com.todostudy.todo.vo.ValidationGroup.UpdateTodo.class})
    private String expDate;

    private String delYn;
}

