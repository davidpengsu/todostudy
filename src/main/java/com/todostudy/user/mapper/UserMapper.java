package com.todostudy.user.mapper;

import com.todostudy.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //가입자 데베저장
    void save(UserVO user);

    //조회
    UserVO findByUserId(UserVO user);
}
