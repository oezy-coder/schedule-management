package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getNickname(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();

        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getNickname(),
                    schedule.getContents(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 일정 수정
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));

        // 비밀번호 검증
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "비밀번호를 다시 입력해주세요."
            );
        }

        schedule.updateSchedule(
                request.getTitle(),
                request.getNickname()
        );
        scheduleRepository.flush();
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getNickname(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
