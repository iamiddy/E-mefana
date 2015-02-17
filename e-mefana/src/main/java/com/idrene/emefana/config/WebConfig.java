/**
 * 
 */
package com.idrene.emefana.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mangofactory.swagger.plugin.EnableSwagger;

/**
 * @author iddymagohe
 *
 */
@Configuration
@EnableWebMvc
@EnableSwagger
@EnableSpringDataWebSupport
@EnableMongoRepositories("com.idrene.emafana.repositories")
@ComponentScan(basePackages = {"com.idrene.emefana.config","com.idrene.emefana.rest.controllers","com.idrene.emefana.rest.tokens"})
public class WebConfig extends WebMvcConfigurerAdapter {
	

	@Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    registry.addResourceHandler("/specs/**").addResourceLocations("/public/apis/");
//    registry.addResourceHandler("/providers/**").addResourceLocations("/app/providers/");
    registry.addResourceHandler("/app/**").addResourceLocations("/app/");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    registry.addInterceptor(localeChangeInterceptor);
  }

  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
    cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en"));
    return cookieLocaleResolver;
  }

//  @Bean
//  public ServletContextTemplateResolver templateResolver() {
//    ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
//    resolver.setPrefix("/WEB-INF/views/");
//    resolver.setSuffix(".html");
//    //NB, selecting HTML5 as the template mode.
//    resolver.setTemplateMode("HTML5");
//    resolver.setCacheable(false);
//    return resolver;
//
//  }

//  public SpringTemplateEngine templateEngine() {
//    SpringTemplateEngine engine = new SpringTemplateEngine();
//    engine.setTemplateResolver(templateResolver());
//    return engine;
//  }

//  @Bean
//  public ViewResolver viewResolver() {
//
//    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//    viewResolver.setTemplateEngine(templateEngine());
//    viewResolver.setOrder(1);
//    viewResolver.setViewNames(new String[]{"*"});
//    viewResolver.setCache(false);
//    return viewResolver;
//  }

  @Bean
  public MessageSource messageSource() {

    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasenames("classpath:messages/messages");
   // messageSource.setBasenames("classpath:messages/messages", "classpath:messages/validation");
    // if true, the key of the message will be displayed if the key is not
    // found, instead of throwing a NoSuchMessageException
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setDefaultEncoding("UTF-8");
    // # -1 : never reload, 0 always reload
    messageSource.setCacheSeconds(0);
    return messageSource;
  }

}
