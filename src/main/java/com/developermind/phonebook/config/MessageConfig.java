package com.developermind.phonebook.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setAlwaysUseMessageFormat(true);
		reloadableResourceBundleMessageSource.setCacheMillis(3600);
		reloadableResourceBundleMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
		reloadableResourceBundleMessageSource.setFallbackToSystemLocale(true);
		reloadableResourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
		reloadableResourceBundleMessageSource.setBasenames("classpath:locale/messages");
		return reloadableResourceBundleMessageSource;
	}

}
