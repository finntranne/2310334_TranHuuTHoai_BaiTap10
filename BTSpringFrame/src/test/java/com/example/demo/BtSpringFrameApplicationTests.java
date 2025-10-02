package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootTest
@SpringBootApplication(scanBasePackages = {"com.example.demo.Controller"})
@ComponentScan
class BtSpringFrameApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {


	 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();


	 viewResolver.setViewClass(JstlView.class);


	 viewResolver.setPrefix("/WEB-INF/views/");


	 viewResolver.setSuffix(".jsp");


	 return viewResolver;


	 }

}
