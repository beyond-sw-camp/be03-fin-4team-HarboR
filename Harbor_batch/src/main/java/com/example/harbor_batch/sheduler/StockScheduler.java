//package com.example.harbor_batch.sheduler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Slf4j
//@EnableScheduling
//public class StockScheduler {
//    private final JobLauncher jobLauncher;
//    private final Job job;
//
//    public StockScheduler(JobLauncher jobLauncher, @Qualifier("StockJob") Job job) {
//        this.jobLauncher = jobLauncher;
//        this.job = job;
//    }
//
//    @Scheduled(fixedDelay = 10000)
//    public void EmployeeSchedule() {
//        try {
//            Map<String, JobParameter> jobParametersMap = new HashMap<>();
//
//            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date time = new Date();
//
//            String time1 = format1.format(time);
//
//            jobParametersMap.put("requestDate", new JobParameter(time1));
//
//            JobParameters parameters = new JobParameters(jobParametersMap);
//
//            JobExecution jobExecution = jobLauncher.run(job, parameters);
//
//        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
//                 JobParametersInvalidException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
