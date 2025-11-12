package com.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String nickname;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(nullable = false)
    private String contents;

    // 생성자
    public Schedule(String title, String nickname, String password, String contents) {
        this.title = title;
        this.nickname = nickname;
        this.password = password;
        this.contents = contents;
    }

    // 기능
    public void updateSchedule(String title, String nickname) {
        this.title = title;
        this.nickname = nickname;
    }
}
