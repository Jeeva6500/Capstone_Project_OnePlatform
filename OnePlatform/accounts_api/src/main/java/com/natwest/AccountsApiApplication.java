package com.natwest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.natwest.filter.AccountsFilter;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApiApplication.class, args);
		// TODO Auto-generated method stub
	}
		//@Bean
		//public FilterRegistrationBean jwtFilter() {
		//	final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		//	registrationBean.setFilter(new AccountsFilter());
		//	registrationBean.addUrlPatterns("/api/accounts/*");
		//	
		//	return registrationBean;
			
	//	}


}
