package com.habin.MovieAPP.module.encrypt;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class EncryptInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

        String password = applicationContext.getEnvironment().getProperty("encrypt");
		System.setProperty("jasypt.encryptor.password", password);

	}
    
}
