package com.todostudy.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String userId;
    private String userName;
    private String password;
    private String regDate;
    private String lastLgnTime;
    private String role;
    private String delYn;

    @Builder
    public User(String userId, String userName, String password, String regDate, String lastLgnTime, String role, String delYn) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.regDate = regDate;
        this.lastLgnTime = lastLgnTime;
        this.role = role;
        this.delYn = delYn;
    }
}
