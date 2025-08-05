package com.todostudy.user.vo;

import com.todostudy.cmn.ObjResVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TokenResVO {

    private String message;
    private String token;

}
