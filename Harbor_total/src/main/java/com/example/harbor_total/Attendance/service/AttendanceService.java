package com.example.harbor_total.Attendance.service;


import com.example.harbor_total.Annual.domain.Annual;
import com.example.harbor_total.Annual.repository.AnnualRepository;
import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.Attendance.dto.request.AttendanceFlexibleWorkReqDto;
import com.example.harbor_total.Attendance.dto.response.AttendanceListResDto;
import com.example.harbor_total.Attendance.repository.AttendanceRepository;
import com.example.harbor_total.Employee.repository.EmployeeRepository;
import com.example.harbor_total.Employee.domain.Employee;
import com.example.harbor_total.Attendance.dto.EmployeeStatusDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.ArrayList;
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
        if (attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O07.name())) {
            return requestflexiblework(attendanceFlexibleWorkReqDto, employeeId);
//        휴     가 (반차, 병가, 반차)
        } else if (attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O04.name()) ||
                attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O08.name()) ||
                attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O03.name()) ||
                attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O09.name())) {
            return requestvacation(attendanceFlexibleWorkReqDto, employeeId);
        } else {
            throw new IllegalArgumentException("해당하는 근무는 없습니다.");
        }
    }

    //    휴가, 유연근무제 확인
    public void checkwork(AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, String employeeId) {
        // 신청한 근무의 시작일과 종료일을 설정합니다.
        LocalDateTime startDate = attendanceFlexibleWorkReqDto.getWorkStartTime().toLocalDate().atStartOfDay();
        LocalDateTime endDate = attendanceFlexibleWorkReqDto.getWorkEndTime().toLocalDate().atTime(23, 59, 59);


        // 유연 근무제나 출장이 이미 예정되어 있는지 확인합니다.
        List<Attendance> flexibleWork = attendanceRepository.findByWorkStartTimeBetweenAndEmployeeEmployeeId(
                attendanceFlexibleWorkReqDto.getWorkStartTime(),
                attendanceFlexibleWorkReqDto.getWorkEndTime(), employeeId);
        if (!flexibleWork.isEmpty()) {
            throw new IllegalArgumentException("유연 근무제나 출장이 이미 예정되어 있습니다. 근무를 확인해 주세요");
        }

        // 직원이 신청한 휴가 목록의 ID를 가져오기
        List<Long> vacationIdsByEmployeeAndPolicy = attendanceRepository.findAttendanceIdsByEmployeeIdAndWorkPolicy(employeeId, attendanceFlexibleWorkReqDto.getWorkPolicy());

        // 직원이 신청한 휴가 중 이미 신청한 휴가와 겹치는지 확인하기.
        List<Annual> overlappingVacations = annualRepository.CheckMyVactionSchedule(
                endDate, startDate,
                startDate, endDate,
                vacationIdsByEmployeeAndPolicy);
        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("이미 그 날짜에 휴가가 있습니다");
        }
    }

    // 유연 근무제 신청
    private AttendanceListResDto requestflexiblework(AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, String employeeId) {
        LocalTime startRange = LocalTime.of(8, 0);
        LocalTime endRange = LocalTime.of(10, 0);

        LocalTime workStartTime = attendanceFlexibleWorkReqDto.getWorkStartTime().toLocalTime();

        if (workStartTime.isBefore(startRange) || workStartTime.isAfter(endRange)) {
            throw new IllegalArgumentException("오전 8시에서 오전 10시 사이에만 가능합니다.");
        }
        List<Attendance> byWorkStartTimeBetween =
                attendanceRepository.findByWorkStartTimeBetweenAndEmployeeEmployeeId(
                        attendanceFlexibleWorkReqDto.getWorkStartTime(),
                        attendanceFlexibleWorkReqDto.getWorkEndTime(), employeeId);
        if (byWorkStartTimeBetween.size() >= 1) {
            throw new IllegalArgumentException("이미 신청이 되어있습니다.");
        }
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        //  유연 근무제 생성
        Attendance attendance = Attendance.create(employee, attendanceFlexibleWorkReqDto);
        // 저장
        attendanceRepository.save(attendance);
        // dto 반환
        return AttendanceListResDto.toDto(attendance, attendanceFlexibleWorkReqDto, employeeId);
    }

    //   휴가 신청
    private AttendanceListResDto requestvacation(AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, String employeeId) {
        if (attendanceFlexibleWorkReqDto.getThirdSignId().isBlank() || attendanceFlexibleWorkReqDto.getFirstSignId().isBlank() || attendanceFlexibleWorkReqDto.getSecondSignId().isBlank()) {
            throw new IllegalArgumentException("결재 받을 사람이 없습니다.");
        }
        checkwork(attendanceFlexibleWorkReqDto, employeeId);
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        // Attendance 엔티티 생성 및 저장
        Attendance attendance = Attendance.create(employee, attendanceFlexibleWorkReqDto);
        attendanceRepository.save(attendance);

        // 휴가 기간에 따른 연차 사용량 계산
        Duration duration = Duration.between(attendanceFlexibleWorkReqDto.getWorkStartTime(), attendanceFlexibleWorkReqDto.getWorkEndTime());
        double useAnnual;
        if (attendanceFlexibleWorkReqDto.getWorkPolicy().equals(O03.name())) {
            useAnnual = 0;
        } else {
            LocalDate startDate = attendanceFlexibleWorkReqDto.getWorkStartTime().toLocalDate();
            LocalDate endDate = attendanceFlexibleWorkReqDto.getWorkEndTime().toLocalDate();

            long totalDays = duration.toDays() + 1; // 휴가 기간의 총 일 수 (종료일을 포함하기 위해 +1)

            long weekendDays = startDate.datesUntil(endDate.plusDays(1))
                    .filter(date -> date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
                    .count(); // 휴가 기간에 포함된 주말의 일 수

            // 실제 사용된 연차 수 계산 (휴가 기간 - 주말)
            useAnnual = Math.max(totalDays - weekendDays, 0.5);
        }
        double remainVacation = employee.getAnnualRemain() - useAnnual;
        if (remainVacation < 0) {
            throw new IllegalArgumentException("남은 휴가일 보다 사용 휴가일이 더 많습니다.");
        }

        // Annual 엔티티 생성
        Annual annual = Annual.create(remainVacation, attendanceFlexibleWorkReqDto, attendance);
        // Annual 엔티티 저장
        annualRepository.save(annual);

        // Attendance 엔티티의 annual 필드 업데이트
        attendance.updateAttendanceId(annual);

        employee.updateRemain_Annual(remainVacation);
        // DTO 반환
        return AttendanceListResDto.toDto(annual, attendanceFlexibleWorkReqDto, employeeId);
    }

    public Optional<List<Object[]>> getListGroupByTeam(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        String MyTeamCode = employee.getTeamCode();

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(23, 59, 59);


        Optional<List<Object[]>> byMyTeamAttendanceCount = employeeRepository.findByMyTeamAttendanceCount(MyTeamCode);
        return byMyTeamAttendanceCount;
    }

    public List<EmployeeStatusDto> getEmployeeStatus(List<String> employeeId) {
        LocalDateTime start = LocalDate.now().atTime(0,0,0);
        LocalDateTime end = LocalDate.now().atTime(23,59,59);
        List<Attendance> attendances = attendanceRepository.findAttendanceByEmployee_EmployeeIdInAndWorkStartTimeLessThanEqualAndWorkEndTimeGreaterThanEqualOrderByCreatedAtDesc(employeeId, end, start);
        List<EmployeeStatusDto> employeeStatusDtos = new ArrayList<>();
        for(Attendance attendance: attendances){
            employeeStatusDtos.add(
                    EmployeeStatusDto.builder()
                            .employeeId(attendance.getEmployee().getEmployeeId())
                            .statusCode(attendance.getWorkPolicy())
                            .build());
        }
        return employeeStatusDtos;
    }
}
