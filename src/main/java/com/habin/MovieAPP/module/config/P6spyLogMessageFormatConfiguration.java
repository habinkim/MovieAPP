package com.habin.MovieAPP.module.config;

import javax.annotation.PostConstruct;

import com.p6spy.engine.spy.P6SpyOptions;

import org.springframework.context.annotation.Configuration;

@Configuration
public class P6spyLogMessageFormatConfiguration {

    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spySqlFormatConfiguration.class.getName());
    }
    
}
