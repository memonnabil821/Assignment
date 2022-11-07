package com.assignment.Assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
@ComponentScan("com.assignment")
public class AssignmentApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
		LOGGER.info("**********Server is up-------");
	}

}
