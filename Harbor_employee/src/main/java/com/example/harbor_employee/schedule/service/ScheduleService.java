package com.example.harbor_employee.schedule.service;

import com.example.harbor_employee.schedule.domain.Schedule;
import com.example.harbor_employee.schedule.dto.ScheduleCreateReq;
import com.example.harbor_employee.schedule.dto.ScheduleListRes;
import com.example.harbor_employee.schedule.dto.ScheduleUpdateReq;
import com.example.harbor_employee.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public void CreateSchedule(ScheduleCreateReq scheduleCreateReq) {
        Schedule schdule = Schedule.builder()
                .scheduleStartDate(scheduleCreateReq.getScheduleStartDate())
                .scheduleEndDate(scheduleCreateReq.getScheduleEndDate())
                .scheduleStartTime(scheduleCreateReq.getScheduleStartTime())
                .scheduleEndTime(scheduleCreateReq.getScheduleEndTime())
                .scheduleTitle(scheduleCreateReq.getScheduleTitle())
                .scheduleComment(scheduleCreateReq.getScheduleComment())
                .scheduleColor(scheduleCreateReq.getScheduleColor())
                .build();
        scheduleRepository.save(schdule);
    }

    public List<ScheduleListRes> findAllSchedule() {
        return scheduleRepository.findAllByDelYnIs(false)
                .stream()
                .map(ScheduleListRes::mapToSchedule)
                .collect(Collectors.toList());
    }

    public Schedule scheduleUpdate(Long scheduleId, ScheduleUpdateReq scheduleUpdateReq) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄입니다."));
        schedule.updateSchedule(scheduleUpdateReq.getScheduleStartDate(), scheduleUpdateReq.getScheduleEndDate(), scheduleUpdateReq.getScheduleStartTime(), scheduleUpdateReq.getScheduleEndTime(),
                scheduleUpdateReq.getScheduleComment(), scheduleUpdateReq.getScheduleTitle());
        return schedule;
    }

    public void ScheduleDelete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄입니다."));
        schedule.deleteSchedule();
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

