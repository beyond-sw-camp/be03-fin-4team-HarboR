//package com.example.harbor_batch.job;
//
//import com.example.harbor_batch.config.JobDataHolder;
//import com.example.harbor_batch.entity.*;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import javax.persistence.EntityNotFoundException;
//import java.lang.reflect.Array;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Optional;
//
//@Configuration
//@Slf4j
//@RequiredArgsConstructor
//public class StockConfiguration {
//    private final JobLauncher jobLauncher;
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final StockService stockService;
//    //    static ArrayList<String> stock = new ArrayList<String>(); // 암기 필요
//    private final JobDataHolder jobDataHolder;
//
//
//
//    @Bean(name = "StockJob")
//    public Job Job() throws Exception {
//        String uniqueJobName = "scheduleViewsJob-" + System.currentTimeMillis();
//        return jobBuilderFactory.get(uniqueJobName)
//                .start(simpleStep())
//                .build();
//    }
//
//    public Step simpleStep() {
//        return this.stepBuilderFactory
//                .get("simple-step1")
//                .tasklet(simpleTasklet())
//                .build();
//    }
//
//    public Tasklet simpleTasklet() {
//        return (stepContribution, chunkContext) -> {
//            try {
//                log.info("주식 호출");
//                KospiStockDto kosPiStockList = stockService.getKosPiStockList();
//                jobDataHolder.add(kosPiStockList.getPrice());
//                log.info("jobDataHolder.getStockList() : {} " ,jobDataHolder.getStockList());
//            } catch (Exception e) {
//                log.error("Error in tasklet execution", e);
//                throw new IllegalArgumentException("Error occurred: " + e.getMessage());
//            }
//            return RepeatStatus.FINISHED;
//        };
//    }
//}