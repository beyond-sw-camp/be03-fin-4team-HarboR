package com.example.harbor_batch.job;

import com.example.harbor_batch.entity.Employee;
import com.example.harbor_batch.entity.EmployeeRepository;
import com.example.harbor_batch.entity.PersonnelAppointment;
import com.example.harbor_batch.entity.PersonnelAppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class EmployeeConfiguration {
    private final JobLauncher jobLauncher;
    private final JobBuilderFactory jobBuilderFactory;
    private final EmployeeRepository employeeRepository;
    private final StepBuilderFactory stepBuilderFactory;
    private final PersonnelAppointmentRepository parepository;
    private final StringRedisTemplate stringRedisTemplate;

    @Bean(name = "EmployeeJob")
    public Job Job() throws Exception {
        String uniqueJobName = "scheduleViewsJob-" + System.currentTimeMillis();
        return jobBuilderFactory.get(uniqueJobName)
                .start(simpleStep())
                .build();
    }

    public Step simpleStep() {
        return this.stepBuilderFactory
                .get("simple-step1")
                .tasklet(simpleTasklet())
                .build();
    }

    public Tasklet simpleTasklet() {
        return (stepContribution, chunkContext) -> {
            try {
                log.info(" EmployeeSchedule start");
                // 오늘 날짜 String
                String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // 레디스에서 해당 날짜의 값을
                String value = stringRedisTemplate.opsForValue().get(today);
                if (value != null) {
                    // 가져온 값을 "_"를 기준으로 분리하여 배열로 저장합니다.
                    String[] PA_idlist = value.split("_");
                    for (String PA_id : PA_idlist) { // 3, 5, 7
                        Optional<PersonnelAppointment> byId = parepository.findById(Long.valueOf(PA_id));
                        if (byId.isEmpty()) {
                            continue;
                        }
                        PersonnelAppointment pa = byId.get();
                        Employee employee = employeeRepository.findByEmployeeId(pa.getEmployee().getEmployeeId()).orElseThrow(() -> new EntityNotFoundException(" 없는 사원번호 회원입니다. "));
//                안 바꿨으면 before 를 after로
                        log.info("before change pa updateAfterdepartmentCode {} :", pa.getAfterDepartmentCode());
                        if (pa.getAfterDepartmentCode().isEmpty()) {
                            pa.updateAfterDepartment(pa.getBeforeDepartmentCode());
                        }
                        log.info("change pa updateAfterdepartmentCode  {} : ", pa.getAfterDepartmentCode());

                        employee.updatePA(pa);

                    }
                } else {
                    log.info("No values found for today: {}", today);
                }
//        키 삭제
                stringRedisTemplate.delete(today);
            }catch (Exception e) {
                    log.error("Error in tasklet execution", e);
                new IllegalArgumentException(e + " 에러 발생");
                }

                return RepeatStatus.FINISHED;
            };
        }
    }