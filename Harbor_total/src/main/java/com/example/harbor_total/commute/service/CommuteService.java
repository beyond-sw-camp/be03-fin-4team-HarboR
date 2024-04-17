package com.example.harbor_total.commute.service;

import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.Attendance.repository.AttendanceRepository;
import com.example.harbor_total.Employee.domain.Employee;
import com.example.harbor_total.Employee.repository.EmployeeRepository;
import com.example.harbor_total.commute.domain.CommuteRecord;
import com.example.harbor_total.commute.repository.CommuteRepository;
import com.example.harbor_total.global.support.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CommuteService {

    private final CommuteRepository commuteRepository;

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public CommuteService(CommuteRepository commuteRepository, EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
        this.commuteRepository = commuteRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public String recordAttendance(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사원 ID입니다."));

        // 해당 사원의 오늘 날짜에 대한 출근 기록이 이미 존재하는지 확인
        boolean hasAttendanceRecord = commuteRepository.existsByEmployeeAndAttendanceDate(employee, Date.valueOf(LocalDate.now()));
        // 휴가 출장 근무 확인
        if (hasAttendanceRecord) {
            throw new IllegalArgumentException("이미 출근 기록이 있는 사원입니다");
        }
        Optional<Attendance> hasAttendanceToday = attendanceRepository.findByEmployeeAndWorkStartTimeBetween(
                employee,
                LocalDate.now().atStartOfDay(), // 오늘 날짜의 시작 시간
                LocalDate.now().atTime(23, 59, 59) // 오늘 날짜의 마지막 시간
        );
        hasAttendanceToday.ifPresent((x) -> {
            throw new IllegalArgumentException(String.valueOf(Code.valueOf(hasAttendanceToday.get().getWorkPolicy()).getMessage()) + " 가 존재합니다");
        });

        // 출근 기록을 생성합니다.
        CommuteRecord commuteRecord = CommuteRecord.createCommuteRecord(employee);

        Attendance attendance = Attendance.create(employee);
        // 출근 기록을 저장합니다.

        commuteRepository.save(commuteRecord);
        attendanceRepository.save(attendance);
        return "ok";
    }

//    public String recordLeaveWork(String employeeId) {
//        // 사원 ID로 Employee 엔티티를 찾습니다.
//        Employee employee = employeeRepository.findByEmployeeId(employeeId)
//                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사원 ID입니다."));
//
//        // 오늘 날짜에 해당하는 출퇴근 기록을 찾습니다.
//        CommuteRecord commuteRecord = commuteRepository.findByEmployeeAndAttendanceDate(employee, Date.valueOf(LocalDate.now()))
//                .orElseThrow(() -> new IllegalArgumentException("오늘 날짜에 해당하는 출퇴근 기록이 없습니다."));
//
//        // 퇴근 시간을 현재 시간으로 설정합니다.
//        commuteRecord.setLeaveworkTime(Time.valueOf(LocalTime.now()));
//
//        // 출퇴근 기록을 저장합니다.
//        commuteRepository.save(commuteRecord);
//
//        return "ok";
//    }
}
