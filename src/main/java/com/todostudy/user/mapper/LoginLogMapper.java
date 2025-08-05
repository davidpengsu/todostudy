package com.todostudy.user.mapper;

import com.todostudy.user.dto.LoginLogDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper {
    void insertLog(LoginLogDto logDto);
}
