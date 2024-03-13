package com.rodrigues.propappstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PropAppStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropAppStudyApplication.class, args);
	}

}
