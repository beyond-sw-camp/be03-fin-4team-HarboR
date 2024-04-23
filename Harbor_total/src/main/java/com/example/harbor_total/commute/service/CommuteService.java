package com.example.harbor_total.commute.service;

import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.Attendance.repository.AttendanceRepository;
import com.example.harbor_total.Employee.domain.Employee;
import com.example.harbor_total.Employee.repository.EmployeeRepository;
import com.example.harbor_total.commute.domain.Commute;
import com.example.harbor_total.commute.dto.req.CommuteListReqDto;
import com.example.harbor_total.commute.dto.res.CommuteListResDto;
import com.example.harbor_total.commute.repository.CommuteRepository;
import com.example.harbor_total.global.support.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.harbor_total.global.support.Code.*;

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

//       휴가, 유연근무제, 출장이 존재하는경우
        if (hasAttendanceToday.isPresent()) {
//            반차, 유연 근무제인경우
            if (O07.name().equals(hasAttendanceToday.get().getWorkPolicy()) ||
                    O08.name().equals(hasAttendanceToday.get().getWorkPolicy())) {
//                오후에 쉬는 경우 오전 출근은 찍되 퇴근은 안 찍음
//                오전에 쉬는경우 오후에 와서 출근 찍고 퇴근도 찍음
//                시작시간을 출근으로 바꿔줌, 퇴근은 안 찍는걸로,오전에 휴가였음 오후에 와야함
                Commute commute = Commute.createCommuteRecord(employee);
                commuteRepository.save(commute);
            } else {
                throw new IllegalArgumentException(String.valueOf(Code.valueOf(hasAttendanceToday.get().getWorkPolicy()).getMessage()) + " 가 존재합니다");
            }
        } else {
//           출근 기록을 생성.
            Commute commute = Commute.createCommuteRecord(employee);
//           지각 비교할 출근 기록 생성
            Attendance attendance = Attendance.create(employee);
//            저장
            commuteRepository.save(commute);
            attendanceRepository.save(attendance);
        }
        return "ok";
    }

    public String recordLeaveWork(String employeeId) {
        // 사원 ID로 Employee 엔티티를 찾습니다.
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사원 ID입니다."));

        // 오늘 날짜에 해당하는 출퇴근 기록을 찾습니다.
        Commute commute = commuteRepository.findByEmployeeAndAttendanceDate(employee, Date.valueOf(LocalDate.now()))
                .orElseThrow(() -> new IllegalArgumentException("오늘 날짜에 해당하는 실제 출퇴근 기록이 없습니다."));
        // 오늘 날짜의 근무 가져옴
        Optional<Attendance> hasAttendanceToday = attendanceRepository.findByEmployeeAndWorkStartTimeBetween(
                employee,
                LocalDate.now().atStartOfDay(), // 오늘 날짜의 시작 시간
                LocalDate.now().atTime(23, 59, 59) // 오늘 날짜의 마지막 시간
        );

        if (hasAttendanceToday.isPresent()) {
//            반차, 유연 근무제인경우
            if (O07.name().equals(hasAttendanceToday.get().getWorkPolicy()) ||
                    O08.name().equals(hasAttendanceToday.get().getWorkPolicy())) {
//                 끝나는 시간 바꿔줌
            }
        }
        commute.updateLeaveTime(Time.valueOf(LocalTime.now()));
        return "ok";
//    }
    }
    public List<CommuteListResDto> MonthlyAttendance(String employeeId, CommuteListReqDto commuteListReqDto) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException(" 없는 회원입니다. "));

        LocalDateTime month = commuteListReqDto.getMonth();
        Date firstDayOfMonth = Date.valueOf(month.toLocalDate().withDayOfMonth(1));
        Date lastDayOfMonth = Date.valueOf(month.toLocalDate().withDayOfMonth(month.toLocalDate().lengthOfMonth()));

//        한달간 내 출퇴근
        List<Commute> MonthCommute = commuteRepository.findAllByEmployeeAndAttendanceDateBetween(employee, firstDayOfMonth, lastDayOfMonth);
        List<Attendance> allByEmployeeAndWorkStartTimeBetween = attendanceRepository.findAllByEmployeeAndWorkStartTimeBetween(employee, month.toLocalDate().withDayOfMonth(1).atStartOfDay(), month.toLocalDate().withDayOfMonth(month.toLocalDate().lengthOfMonth()).atTime(23, 59, 59))
                .orElseThrow(()-> new IllegalArgumentException(String.valueOf(month.getMonth()) + "의 해당하는 근무표가 없습니다."));

        log.info("MonthCommute : {}", MonthCommute);
        log.info("allByEmployeeAndWorkStartTimeBetween : " , allByEmployeeAndWorkStartTimeBetween + "입니다.");

        return allByEmployeeAndWorkStartTimeBetween.stream()
                .flatMap(attendance -> CommuteListResDto.toDto(attendance, MonthCommute).stream())
                .sorted(Comparator.comparing(CommuteListResDto::getAttendanceDate))
                .collect(Collectors.toList());
    }
}
