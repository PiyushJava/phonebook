package com.developermind.phonebook.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setAlwaysUseMessageFormat(true);
		resourceBundleMessageSource.setCacheMillis(3600);
		resourceBundleMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
		resourceBundleMessageSource.setFallbackToSystemLocale(true);
		resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
		resourceBundleMessageSource.addBasenames("message.properties");
		return resourceBundleMessageSource;
	}

}
