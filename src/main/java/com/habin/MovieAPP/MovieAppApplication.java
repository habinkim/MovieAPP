package com.habin.MovieAPP;

import com.habin.MovieAPP.module.encrypt.EncryptInitializer;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableBatchProcessing
@SpringBootApplication
public class MovieAppApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MovieAppApplication.class)
				.initializers(new EncryptInitializer())
				.run(args);
	}

}
