package com.example.harbor_employee.global.util;

import com.example.harbor_employee.Employee.domain.Department;
import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.repository.DepartmentRepository;
import com.example.harbor_employee.Employee.repository.EmployeeRepository;
import com.example.harbor_employee.PersonnelAppointment.domain.PersonnelAppointment;
import com.example.harbor_employee.PersonnelAppointment.repository.PersonnelAppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Transactional
@Slf4j
public class EmployeeScheduler {
    private final PersonnelAppointmentRepository parepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final StringRedisTemplate stringRedisTemplate;

    public EmployeeScheduler(PersonnelAppointmentRepository parepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, StringRedisTemplate stringRedisTemplate) {
        this.parepository = parepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void EmployeeSchedule() {
        log.info(" EmployeeSchedule start");

        // 오늘 날짜 String
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 레디스에서 해당 날짜의 값을
        String value = stringRedisTemplate.opsForValue().get(today);
        if (value != null) {
            // 가져온 값을 "_"를 기준으로 분리하여 배열로 저장합니다.
            String[] PA_idlist = value.split("_");
            for (String PA_id : PA_idlist) { // 3, 5, 7
                PersonnelAppointment pa = parepository.findById(Long.valueOf(PA_id)).orElseThrow(() -> new EntityNotFoundException("없는 결재 입니다."));
                Employee employee = employeeRepository.findByEmployeeId(pa.getEmployee().getEmployeeId()).orElseThrow(() -> new EntityNotFoundException(" 없는 사원번호 회원입니다. "));
                if (pa.getAfterDepartmentCode().isEmpty()) {
                    pa.before_to_After(pa.getBeforeDepartmentCode());
                }
                System.out.println(pa.getAfterDepartmentCode());
                Department department = departmentRepository.findByDepartmentCode(pa.getAfterDepartmentCode()).orElseThrow(() -> new IllegalArgumentException("없는 부서입니다."));
                employee.updatePA(pa, department);
            }

        } else {
            log.info("No values found for today: {}", today);
        }

//        lst에서 하나씩 뽑아서 객체들 다 바꿔줌.

    }
}

