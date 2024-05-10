//package com.example.harbor_batch.job;
//
//import com.example.harbor_batch.config.JobDataHolder;
//import com.example.harbor_batch.entity.KospiStockDto;
//import com.example.harbor_batch.entity.StockService;
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
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@Slf4j
//@RequiredArgsConstructor
//public class ResetStockConfiguration {
//    private final JobLauncher jobLauncher;
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final StockService stockService;
//    private final JobDataHolder jobDataHolder;
//
//
//
//
//    @Bean(name = "ResetStockJob")
//    public Job Job() throws Exception {
//        String uniqueJobName = "ResetStockJob-" + System.currentTimeMillis();
//        return jobBuilderFactory.get(uniqueJobName)
//                .start(simpleStep())
//                .build();
//    }
//
//    public Step simpleStep() {
//        return this.stepBuilderFactory
//                .get("ResetStockJob-step1")
//                .tasklet(simpleTasklet())
//                .build();
//    }
//
//    public Tasklet simpleTasklet() {
//        return (stepContribution, chunkContext) -> {
//            try {
//                log.info("ResetStockJob 리셋");
//                List<String> stockList = jobDataHolder.getStockList();
//                log.info("stockList.size() : {}", stockList.size());
//                stockList.clear();
//                log.info("stockList.size() : {}", stockList.size());
//            } catch (Exception e) {
//                log.error("Error in tasklet execution", e);
//                new IllegalArgumentException(e + " 에러 발생");
//            }
//
//            return RepeatStatus.FINISHED;
//        };
//    }
//}