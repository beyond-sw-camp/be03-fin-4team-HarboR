//package com.example.harbor_employee.global.util;
//
//import com.example.harbor_employee.Employee.domain.Employee;
//import com.example.harbor_employee.Employee.repository.EmployeeRepository;
//import com.example.harbor_employee.PersonnelAppointment.domain.PersonnelAppointment;
//import com.example.harbor_employee.PersonnelAppointment.repository.PersonnelAppointmentRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Optional;
//
//@Component
//@Transactional
//@Slf4j
//public class EmployeeScheduler {
//    private final PersonnelAppointmentRepository parepository;
//    private final EmployeeRepository employeeRepository;
//    private final StringRedisTemplate stringRedisTemplate;
//
//    public EmployeeScheduler(PersonnelAppointmentRepository parepository, EmployeeRepository employeeRepository, StringRedisTemplate stringRedisTemplate) {
//        this.parepository = parepository;
//        this.employeeRepository = employeeRepository;
//        this.stringRedisTemplate = stringRedisTemplate;
//    }
//
//    @Scheduled(cron = "0 0 0 * * *")
//    public void EmployeeSchedule() {
//        log.info(" EmployeeSchedule start");
//
//        // 오늘 날짜 String
//        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        // 레디스에서 해당 날짜의 값을
//        String value = stringRedisTemplate.opsForValue().get(today);
//        if (value != null) {
//            // 가져온 값을 "_"를 기준으로 분리하여 배열로 저장합니다.
//            String[] PA_idlist = value.split("_");
//            for (String PA_id : PA_idlist) { // 3, 5, 7
//                Optional<PersonnelAppointment> byId = parepository.findById(Long.valueOf(PA_id));
//                if (byId.isEmpty()) {
//                    continue;
//                }
//                PersonnelAppointment pa = byId.get();
//                Employee employee = employeeRepository.findByEmployeeId(pa.getEmployee().getEmployeeId()).orElseThrow(() -> new EntityNotFoundException(" 없는 사원번호 회원입니다. "));
////                안 바꿨으면 before 를 after로
//                log.info("before change pa updateAfterdepartmentCode {} :", pa.getAfterDepartmentCode());
//                if (pa.getAfterDepartmentCode().isEmpty()) {
//                    pa.updateAfterDepartment(pa.getBeforeDepartmentCode());
//                }
//                log.info("change pa updateAfterdepartmentCode  {} : ", pa.getAfterDepartmentCode());
//
//                employee.updatePA(pa);
//
//            }
//        } else {
//            log.info("No values found for today: {}", today);
//        }
////        키 삭제
//        stringRedisTemplate.delete(today);
//
//    }
//}
//
