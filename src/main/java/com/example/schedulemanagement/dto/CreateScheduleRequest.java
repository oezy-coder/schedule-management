package com.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    // 속성
    private String title;
    private String nickname;
    private String password;
    private String contents;
}
