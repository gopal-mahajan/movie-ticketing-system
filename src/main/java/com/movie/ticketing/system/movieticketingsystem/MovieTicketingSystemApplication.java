package com.movie.ticketing.system.movieticketingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MovieTicketingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketingSystemApplication.class, args);
	}
}
