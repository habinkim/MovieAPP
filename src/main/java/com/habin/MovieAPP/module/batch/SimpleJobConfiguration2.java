package com.habin.MovieAPP.module.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Profile("batch")
@Configuration // * Spring Batch의 모든 Job은 @Configuration으로 등록해서 사용
public class SimpleJobConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob2() {
        return jobBuilderFactory.get("simpleJob2") // * SimpleJob 이라는 이름의 Batch Job을 생성
                .start(simpleStep2(null))
                .build();
    }

    @Bean
    public Job simpleJob3() {
        return jobBuilderFactory.get("simpleJob3")
                .start(simpleStep2(null))
                .next(simpleStep3(null))
                .build();
    }

    @Bean
    @JobScope
    public Step simpleStep2(@Value("#{jobParameters[requestDate]}") String requestDate) { // * launch.json의 args에서 Job Parameter 받음
        return stepBuilderFactory.get("simpleStep2")
                .tasklet((contribution, chunkContext) -> { 
                    log.info(">>>>> This is Step2");
                    log.info(">>>>> requestDate = {}", requestDate);
                    return RepeatStatus.FINISHED;
                    // throw new IllegalArgumentException("step2에서 실패합니다");
                })
                .build();
    }

    @Bean
    @JobScope
    public Step simpleStep3(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("simpleStep3")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>>This is Step3");
                    log.info(">>>>> requestDate = {}", requestDate);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    
}
