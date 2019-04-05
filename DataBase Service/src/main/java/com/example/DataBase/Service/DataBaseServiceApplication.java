package com.example.DataBase.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("DBItems")
@SpringBootApplication
public class DataBaseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataBaseServiceApplication.class, args);
	}
	
}
