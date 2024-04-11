package com.example.harbor_total.commute.service;

import com.example.harbor_total.Employee.domain.Employee;
import com.example.harbor_total.Employee.repository.EmployeeRepository;
import com.example.harbor_total.commute.domain.CommuteRecord;
import com.example.harbor_total.commute.repository.CommuteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
public class CommuteService {

    private final CommuteRepository commuteRepository;

    private final EmployeeRepository employeeRepository;

    public CommuteService(CommuteRepository commuteRepository, EmployeeRepository employeeRepository) {
        this.commuteRepository = commuteRepository;
        this.employeeRepository = employeeRepository;
    }

    public String recordAttendance(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사원 ID입니다."));

        // 해당 사원의 오늘 날짜에 대한 출근 기록이 이미 존재하는지 확인합니다.
        boolean hasAttendanceRecord = commuteRepository.existsByEmployeeAndAttendanceDate(employee, Date.valueOf(LocalDate.now()));

        // 출근 기록이 이미 존재하는 경우 메시지를 반환합니다.
        if (hasAttendanceRecord) {
            return "이미 출근이 처리되었습니다.";
        }

        // 출근 기록을 생성합니다.
        CommuteRecord commuteRecord = CommuteRecord.builder()
                .attendanceTime(Time.valueOf(LocalTime.now()))
                .attendanceDate(Date.valueOf(LocalDate.now()))
                .employee(employee)
                .build();

        // 출근 기록을 저장합니다.
        commuteRepository.save(commuteRecord);

        return "ok";
    }


    public String recordLeaveWork(String employeeId) {
        // 사원 ID로 Employee 엔티티를 찾습니다.
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사원 ID입니다."));

        // 오늘 날짜에 해당하는 출퇴근 기록을 찾습니다.
        CommuteRecord commuteRecord = commuteRepository.findByEmployeeAndAttendanceDate(employee, Date.valueOf(LocalDate.now()))
                .orElseThrow(() -> new IllegalArgumentException("오늘 날짜에 해당하는 출퇴근 기록이 없습니다."));

        // 퇴근 시간을 현재 시간으로 설정합니다.
        commuteRecord.setLeaveworkTime(Time.valueOf(LocalTime.now()));

        // 출퇴근 기록을 저장합니다.
        commuteRepository.save(commuteRecord);

        return "ok";
    }
}
