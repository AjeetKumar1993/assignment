package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com" })
public class App{	
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(App.class);
		app.run(args);
	
		System.out.println("*****started successfully*****");
    }
}