/**
 * 
 */
package com.idrene.emefana.config;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.idrene.emefana.util.UtilityBean;

/**
 * @author iddymagohe
 * 
 */
@Configuration
@ComponentScan(basePackages={"com.idrene.emefana.domain","com.idrene.emefana.repositories","com.idrene.emefana.service"})
@PropertySource(value = { "classpath:application.properties" })
public class ServiceConfig {
	
	@Value("$app.encoder.key")
	private String app_encrypt;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// @Bean
	// public JavaMailSenderImpl javaMailSenderImpl() {
	// JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
	// mailSenderImpl.setHost(env.getProperty("smtp.host"));
	// mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
	// mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
	// mailSenderImpl.setUsername(env.getProperty("smtp.username"));
	// mailSenderImpl.setPassword(env.getProperty("smtp.password"));
	//
	// Properties javaMailProps = new Properties();
	// javaMailProps.put("mail.smtp.auth", true);
	// javaMailProps.put("mail.smtp.starttls.enable", true);
	//
	// mailSenderImpl.setJavaMailProperties(javaMailProps);
	//
	// return mailSenderImpl;
	// }
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
	
	@Bean
	public UtilityBean utilityBean(){
		return new UtilityBean();
	}
	
	@Bean
	public TextEncryptor textEncryptor(){
		BasicTextEncryptor encrpt = new BasicTextEncryptor();
		encrpt.setPassword(app_encrypt);
		return encrpt;
	}
	
	@Bean
	public PasswordEncryptor passwordEncryptor(){
		return new BasicPasswordEncryptor();
	}
}
