package com.idrene.emefana.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class RestExporterWebInitializer implements WebApplicationInitializer {

	  @Override public void onStartup(ServletContext servletContext) throws ServletException {

	    // Bootstrap repositories in root application context
	   AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();
	   rootCtx.register(ServiceConfig.class);
	   //rootCtx.register(SecurityConfig.class);
	   rootCtx.register(MongoRepositoryConfig.class); // Include JPA entities, Repositories
	  
	   servletContext.addListener(new ContextLoaderListener(rootCtx));
//	    servletContext.addFilter("springSecurity", DelegatingFilterProxy.class);
			//    servletContext.getFilterRegistration("springSecurity").addMappingForUrlPatterns(
			//        EnumSet.of(DispatcherType.REQUEST),
			//        false,
			//        "/*"
			//    );


	    // Enable Spring Data REST in the DispatcherServlet
	    AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
	    webCtx.register(WebConfig.class);

	    DispatcherServlet dispatcherServlet = new DispatcherServlet(webCtx);
	    //ServletRegistration.Dynamic reg = servletContext.addServlet("rest-exporter", dispatcherServlet);
	    ServletRegistration.Dynamic reg2 = servletContext.addServlet("dispatcher", dispatcherServlet);
	    
	    reg2.setLoadOnStartup(1);
	    reg2.addMapping("/");
	  //  reg.setLoadOnStartup(2);
	  //  reg.addMapping("/*");
	  }
	}