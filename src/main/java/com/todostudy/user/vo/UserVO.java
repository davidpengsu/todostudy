package com.todostudy.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO {

    private String userId;
    private String name;
    private String password;
    private String role;
    private String delYn;
    private String regDate;
    private String lastLgnTime;
    private Integer isNewUser;

}
