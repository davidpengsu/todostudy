package com.todostudy.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO {
    @NotBlank(message = "아이디입력필수에여", groups = {ValidationGroup.Join.class, ValidationGroup.Login.class})
    private String userId;

    @NotBlank(message = "이름입력필수에여", groups = {ValidationGroup.Join.class})
    private String name;

    @NotBlank(message = "비번입력필수에여", groups = {ValidationGroup.Login.class})
    private String password;

    private String role;
    private String delYn;
    private String regDate;
    private String lastLgnTime;
    private Integer isNewUser;

}
