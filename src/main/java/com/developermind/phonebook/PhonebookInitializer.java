package com.developermind.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(mode = AdviceMode.PROXY)
@EnableDiscoveryClient
@SpringBootApplication
public class PhonebookInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PhonebookInitializer.class, args);
	}

}
