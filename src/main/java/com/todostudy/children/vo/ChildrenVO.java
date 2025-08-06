package com.todostudy.children.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ChildrenVO {
    private Integer idx;
    private String userId; //부모(유저임!!!)
    private String name;
    //private String gender;
    private String birthday;
    private String regDate;
    private String delYn;
}
