package com.todostudy.cmn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class ListResVO<T> {

    private final Integer size;
    private final List<T> datas;

}
