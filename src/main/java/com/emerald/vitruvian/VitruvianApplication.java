package com.emerald.vitruvian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class VitruvianApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitruvianApplication.class, args);
	}

}
