package com.natwest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.natwest.filter.CardFilter;

@EnableDiscoveryClient
@SpringBootApplication
public class CardApiApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(CardApiApplication.class, args);
	}
	
	//@Bean
	//public FilterRegistrationBean jwtFilter() {
	//	final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	//	registrationBean.setFilter(new CardFilter());
	//	registrationBean.addUrlPatterns("/api/card/*");
	//	
	//	return registrationBean;
		
	//}

}
