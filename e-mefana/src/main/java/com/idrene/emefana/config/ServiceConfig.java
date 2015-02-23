/**
 * 
 */
package com.idrene.emefana.config;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.idrene.emefana.service.request.converter.ListingResourceToProvider;
import com.idrene.emefana.util.UtilityBean;

/**
 * @author iddymagohe
 * @since 1.0
 * 
 */
@Configuration
@ComponentScan(basePackages = {"com.idrene.emefana.domain",
		"com.idrene.emefana.repositories", "com.idrene.emefana.service",
		"com.idrene.emefana.security" })
@PropertySource(value = { "classpath:application.properties" })
public class ServiceConfig {
	
	@Value("$app.encoder.key")
	private String app_encrypt;
	
	@Value("${mail.smtp.host}")
	private String mailHost;
	
	@Value("${mail.smtp.port}")
	private int mailPort;
	
	@Value("${mail.smtp.protocal}")
	private String mailProtocal;
	
	@Value("${mail.smtp.user}")
	private String mailUser;
	
	@Value("${mail.smtp.password}")
	private String mailPassword;
	
	@Value("${mail.smtp.auth}")
	private boolean mailAuth;
	
	@Value("${mail.smtp.starttls.enable}")
	private boolean mailStarttls;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	 /*
	  * https://support.google.com/mail/answer/13287?hl=en
	  */
	@Bean
	 public JavaMailSenderImpl javaMailSenderImpl() {
	 JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
	 mailSenderImpl.setHost(mailHost);
	 mailSenderImpl.setPort(mailPort);
	// mailSenderImpl.setProtocol(mailProtocal); // use default
	 mailSenderImpl.setUsername(mailUser);
	 mailSenderImpl.setPassword(utilityBean().decodePropertyValue(mailPassword));
	
	 Properties javaMailProps = new Properties();
	 javaMailProps.put("mail.smtp.auth", mailAuth);
	 javaMailProps.put("mail.smtp.starttls.enable", mailStarttls);
	
	 mailSenderImpl.setJavaMailProperties(javaMailProps);
	
	 return mailSenderImpl;
	 }
	
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
	
	@Bean public VelocityEngineFactoryBean velocityEngine(){
		  VelocityEngineFactoryBean velocityEngineFactoryBean=new VelocityEngineFactoryBean();
		  Properties velocityProperties=new Properties();
		  velocityProperties.setProperty("resource.loader","class");
		  velocityProperties.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		  velocityEngineFactoryBean.setVelocityProperties(velocityProperties);
		  return velocityEngineFactoryBean;
		}
	
	@Bean (name="conversionService")
	public ConversionService conversionService(){
		ConversionServiceFactoryBean conversionService = new ConversionServiceFactoryBean();
		conversionService.setConverters(converters());
		conversionService.afterPropertiesSet();
		return conversionService.getObject();
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	private Set<Converter> converters() {
        Set<Converter> converters = new HashSet<>();
        converters.add(new ListingResourceToProvider());
        return converters;
    }
}
