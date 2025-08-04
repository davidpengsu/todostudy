package com.todostudy.user.dto;

import com.todostudy.user.domain.User;
import lombok.Data;

@Data
public class UserJoinRequestDto {
    private String userId;
    private String userName;
    private String password;
}
