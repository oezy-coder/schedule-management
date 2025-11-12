package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 비밀번호 검증
    public void validatePassword(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) < '0' || password.charAt(i) > '9') {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "비밀번호는 숫자만 입력 가능합니다."
                );
            }
        }
    }

    // 일정 생성
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        validatePassword(request.getPassword());
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getNickname(),
                request.getPassword(),
                request.getContents()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getNickname(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }
}
