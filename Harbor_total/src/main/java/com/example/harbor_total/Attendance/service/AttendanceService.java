package com.example.harbor_total.Attendance.service;


import com.example.harbor_total.Annual.domain.Annual;
import com.example.harbor_total.Annual.repository.AnnualRepository;
import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.Attendance.dto.request.AttendanceFlexibleWorkReqDto;
import com.example.harbor_total.Attendance.dto.response.AttendanceListResDto;
import com.example.harbor_total.Attendance.repository.AttendanceRepository;
import com.example.harbor_total.Employee.repository.EmployeeRepository;
import com.example.harbor_total.Employee.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.example.harbor_total.global.support.Code.*;

@Service
@Slf4j
@Transactional
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final AnnualRepository annualRepository;


    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository, AnnualRepository annualRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.annualRepository = annualRepository;
    }
    public AttendanceListResDto requestwork(AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, String employeeId) {

        AttendanceListResDto attendanceListResDto;

//        유연 근무제 (승인 안 받아도 됨)
        if(attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O07.name())) {
            return requestflexiblework(attendanceFlexibleWorkReqDto, employeeId);
//        휴가 (반차, 병가, 반차)
        } else if (attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O04.name()) ||
                attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O08.name()) ||
                attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O09.name())) {
            return requestvacation(attendanceFlexibleWorkReqDto, employeeId);
        } else {
            throw new IllegalArgumentException("해당하는 근무는 없습니다.");
        }
    }

    // 유연 근무제
    private AttendanceListResDto requestflexiblework(AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, String employeeId) {
        LocalTime startRange = LocalTime.of(8, 0);
        LocalTime endRange = LocalTime.of(10, 0);

        LocalTime workStartTime = attendanceFlexibleWorkReqDto.getWorkStartTime().toLocalTime();

        if (workStartTime.isBefore(startRange) || workStartTime.isAfter(endRange)) {
            throw new IllegalArgumentException("오전 8시에서 오전 10시 사이에만 가능합니다.");
        }
        System.out.println("1");
        List<Attendance> byWorkStartTimeBetween =
                attendanceRepository.findByWorkStartTimeBetweenAndEmployeeEmployeeId(
                        attendanceFlexibleWorkReqDto.getWorkStartTime(),
                        attendanceFlexibleWorkReqDto.getWorkEndTime(), employeeId);
        if (byWorkStartTimeBetween.size() >= 1) {
            throw new IllegalArgumentException("이미 신청이 되어있습니다.");
        }
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        System.out.println(employee.getEmployeeId());

        //  유연 근무제 생성
        Attendance attendance = Attendance.create(employee, attendanceFlexibleWorkReqDto);
        // 저장
        attendanceRepository.save(attendance);
        // dto 반환
        return AttendanceListResDto.toDto(attendance);
    }

    //   휴가 신청
    private AttendanceListResDto requestvacation(AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, String employeeId) {

        System.out.println(" 도착 ");
        LocalDateTime startDate = attendanceFlexibleWorkReqDto.getWorkStartTime().toLocalDate().atStartOfDay();
        LocalDateTime endDate = attendanceFlexibleWorkReqDto.getWorkEndTime().toLocalDate().atTime(23, 59, 59);

//        1. 휴가 신청하려는 유연근무제, 출장 확인
        List<Attendance> flexibleWork  =
                attendanceRepository.findByWorkStartTimeBetweenAndEmployeeEmployeeId(
                        attendanceFlexibleWorkReqDto.getWorkStartTime(),
                        attendanceFlexibleWorkReqDto.getWorkEndTime(), employeeId);

        if (flexibleWork.size() >= 1) {
            throw new IllegalArgumentException("유연 근무제, 혹은 출장이 예정되어 있습니다. 근무를 확인해 주세요");
        }

//      2. 그날 휴가가 신청되어 있는지 확인
        Optional<Annual> vacation = annualRepository.findByAdjustmentDateLessThanAndAdjustmentEndDateGreaterThanEqualOrAdjustmentDateLessThanEqualAndAdjustmentEndDateGreaterThan(
                endDate, startDate,
                startDate, endDate);
        vacation.ifPresent(annual -> {
            throw new IllegalArgumentException("휴가 신청 하신 날에 휴가가 이미 있습니다");
        });

//        휴가 신청종류:  반차, 연차, 병가
        return null;
    }
}
