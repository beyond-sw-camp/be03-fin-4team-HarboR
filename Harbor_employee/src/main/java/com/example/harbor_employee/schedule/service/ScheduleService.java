package com.example.harbor_employee.schedule.service;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.repository.EmployeeRepository;
import com.example.harbor_employee.schedule.domain.Schedule;
import com.example.harbor_employee.schedule.dto.ScheduleCreateReq;
import com.example.harbor_employee.schedule.dto.ScheduleListRes;
import com.example.harbor_employee.schedule.dto.ScheduleTeamListRes;
import com.example.harbor_employee.schedule.dto.ScheduleUpdateReq;
import com.example.harbor_employee.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
    }

    public Schedule CreateSchedule(String employeeId, ScheduleCreateReq scheduleCreateReq) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다"));
        Schedule schedule = Schedule.builder()
                .scheduleStartDate(scheduleCreateReq.getScheduleStartDate())
                .scheduleEndDate(scheduleCreateReq.getScheduleEndDate())
                .scheduleStartTime(scheduleCreateReq.getScheduleStartTime())
                .scheduleEndTime(scheduleCreateReq.getScheduleEndTime())
                .scheduleTitle(scheduleCreateReq.getScheduleTitle())
                .scheduleComment(scheduleCreateReq.getScheduleComment())
                .scheduleColor((scheduleCreateReq.getScheduleColor()))
                .employee(employee)
                .build();
        System.out.println(scheduleCreateReq.getScheduleTitle());
        Schedule schedule1 = scheduleRepository.save(schedule);
        return schedule1;
    }

    public List<ScheduleListRes> findAllSchedule(String employeeId) {
        return scheduleRepository.findAllByDelYnIsAndEmployee_EmployeeId(false, employeeId)
                .stream()
                .map(ScheduleListRes::mapToSchedule)
                .collect(Collectors.toList());
    }

    public List<ScheduleTeamListRes> findAllTeamSchedule(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        List<Schedule> scheduleList = scheduleRepository.findAllByDelYnIsAndEmployee_DepartmentCode(false, employee.getDepartmentCode());
        return scheduleList.stream()
                .map(schedule -> ScheduleTeamListRes.mapToSchedule(schedule, schedule.getEmployee().getName()))
                .collect(Collectors.toList());
    }

    public void scheduleUpdate(Long scheduleId, ScheduleUpdateReq scheduleUpdateReq) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄입니다."));
        schedule.updateSchedule(
                scheduleUpdateReq.getScheduleStartDate(),
                scheduleUpdateReq.getScheduleEndDate(),
                scheduleUpdateReq.getScheduleEndTime(),
                scheduleUpdateReq.getScheduleStartTime(),
                scheduleUpdateReq.getScheduleComment(),
                scheduleUpdateReq.getScheduleTitle(),
                scheduleUpdateReq.getScheduleColor()
        );
    }

    public void ScheduleDelete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄입니다."));
        schedule.deleteSchedule();
    }

    public ScheduleTeamListRes scheduleDetail(Long scheduleId) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findByScheduleId(scheduleId);
        return optionalSchedule
                .map(s -> ScheduleTeamListRes.mapToSchedule(s, s.getEmployee().getName()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄 입니다"));
    }
}

