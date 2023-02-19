package com.natwest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

//import com.natwest.filter.TransactionFilter;

@EnableDiscoveryClient
@SpringBootApplication
public class TransactionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApiApplication.class, args);
	}
	
	//@Bean
	//public FilterRegistrationBean jwtFilter() {
	//	final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	//	registrationBean.setFilter(new TransactionFilter());
	//	registrationBean.addUrlPatterns("/api/transaction/*");
	//	
	//	return registrationBean;
	//	
	//}

}
