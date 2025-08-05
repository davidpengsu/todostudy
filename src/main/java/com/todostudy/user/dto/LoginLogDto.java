package com.todostudy.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LoginLogDto {
    private String userId;
    private String lgnIpAddr;
    private String stats;
    private LocalDateTime regDate;
}
