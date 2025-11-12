package com.example.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {

    // 속성
    private final Long id;
    private final String title;
    private final String nickname;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 생성자
    public CreateScheduleResponse(Long id, String title, String nickname, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
