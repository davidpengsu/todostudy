package com.todostudy.children.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChildrenVO {
    private Integer idx;
    private String userId; //부모(유저임!!!)
    private String regDate;
    private String delYn;

    //자녀이름공백x
    @NotBlank(message = "자녀이름필수임")
    private String name;

    //생일
    @NotBlank(message = "생일도입력필수임")
    private String birthdate;

}
