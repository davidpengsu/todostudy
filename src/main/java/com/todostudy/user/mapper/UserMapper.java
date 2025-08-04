package com.todostudy.user.mapper;

import com.todostudy.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //가입자 데베저장
    void save(User user);
}
