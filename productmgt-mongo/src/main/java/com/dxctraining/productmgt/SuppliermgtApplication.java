package com.dxctraining.productmgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SuppliermgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuppliermgtApplication.class, args);

	}

}
