package com.habin.MovieAPP.module.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Profile("batch")
@Configuration // * Spring Batch의 모든 Job은 @Configuration으로 등록해서 사용
public class SimpleJobConfiguration1 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob1() {
        return jobBuilderFactory.get("simpleJob1") // * SimpleJob 이라는 이름의 Batch Job을 생성
                .start(simpleStep1())
                .build();
    }

    @Bean
    @JobScope
    public Step simpleStep1() {
        return stepBuilderFactory.get("simpleStep1") // * Batch Step을 생성
                .tasklet((contribution, chunkContext) -> { // * Step 안에서 단일로 수행될 커스텀한 기능들을 선언할때 사용
                    log.info(">>>>> This is Step1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    // * Spring Batch에서 Job은 하나의 배치 작업 단위
    // * Job 안에는 여러 Step이 존재하고, Step 안에 Tasklet 혹은 Reader & Processor & Writer 묶음이 존재
    // * Tasklet 하나와 Reader & Processor 한 묶음이 같은 레벨
    // * Reader & Processor가 끝나고 Tasklet으로 마무리 짓는 등으로 만들순 없다
    // * Tasklet은 Spring MVC의 @Component, @Bean과 비슷한 역할

}
