package com.todostudy.cmn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class ObjResVO<T> {

    private final T message;

}
