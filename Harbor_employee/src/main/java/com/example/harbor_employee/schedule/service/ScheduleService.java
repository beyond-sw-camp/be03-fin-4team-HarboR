package com.example.harbor_employee.schedule.service;

import com.example.harbor_employee.schedule.domain.Schedule;
import com.example.harbor_employee.schedule.dto.ScheduleCreateReq;
import com.example.harbor_employee.schedule.dto.ScheduleDetailRes;
import com.example.harbor_employee.schedule.dto.ScheduleListRes;
import com.example.harbor_employee.schedule.dto.ScheduleUpdateReq;
import com.example.harbor_employee.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule CreateSchedule(ScheduleCreateReq scheduleCreateReq) {
        Schedule schedule = Schedule.builder()
                .scheduleStartDate(scheduleCreateReq.getScheduleStartDate())
                .scheduleEndDate(scheduleCreateReq.getScheduleEndDate())
                .scheduleStartTime(scheduleCreateReq.getScheduleStartTime())
                .scheduleEndTime(scheduleCreateReq.getScheduleEndTime())
                .scheduleTitle(scheduleCreateReq.getScheduleTitle())
                .scheduleComment(scheduleCreateReq.getScheduleComment())
                .build();
        System.out.println(scheduleCreateReq.getScheduleTitle());
        Schedule schedule1 = scheduleRepository.save(schedule);
        return schedule1;
    }

    public List<ScheduleListRes> findAllSchedule() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleListRes::mapToSchedule)
                .collect(Collectors.toList());
    }

    public Schedule scheduleUpdate(Long scheduleId, ScheduleUpdateReq scheduleUpdateReq) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄입니다."));
        schedule.updateSchedule(scheduleUpdateReq.getScheduleStartDate(), scheduleUpdateReq.getScheduleEndDate(), scheduleUpdateReq.getScheduleStartTime(), scheduleUpdateReq.getScheduleEndTime(),
                scheduleUpdateReq.getScheduleComment(), scheduleUpdateReq.getScheduleTitle());
        return scheduleRepository.save(schedule);

    }

    public void ScheduleDelete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄입니다."));
        schedule.deleteSchedule();
        scheduleRepository.save(schedule);
    }

    public Schedule scheduleDetail(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄 입니다"));
        if (schedule.isDelYn()) {
            return null; // 또는 원하는 처리를 하세요.
        } else {
            return schedule;
        }
    }
}

