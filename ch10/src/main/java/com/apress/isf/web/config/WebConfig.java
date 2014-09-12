package com.apress.isf.web.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.apress.isf.spring" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:/META-INF/data/schema.sql")
				.addScript("classpath:/META-INF/data/data.sql")
				.build();
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jspx");
		return resolver;
	}

	@Bean
	public String query(){
		StringBuilder query = new StringBuilder("select d.documentId, d.name, d.location, d.description as doc_desc, ");
		query.append("d.typeId, d.created, d.modified, ");
		query.append("t.name as type_name, t.description as type_desc, t.extension from ");
		query.append("documents d ");
		query.append("join types t ");
		query.append("on d.typeId = t.typeId");
		return query.toString();
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
		messageResource.setBasenames("WEB-INF/i18n/messages");
		messageResource.setFallbackToSystemLocale(false);
		return messageResource;
	}

	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("locale");
		return localeResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		registry.addInterceptor(localeInterceptor);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");

	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
