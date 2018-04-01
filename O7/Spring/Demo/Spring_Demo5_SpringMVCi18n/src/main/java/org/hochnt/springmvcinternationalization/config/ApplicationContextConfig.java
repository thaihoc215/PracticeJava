package org.hochnt.springmvcinternationalization.config;

import org.hochnt.springmvcinternationalization.interceptor.UrlLocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("org.hochnt.springmvcinternationalization.*")
public class ApplicationContextConfig {
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		//Điều hướng prefix đến pages (nơi chưaấ jsp tương ứng)
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

		// Đọc vào file i18n/messages_xxx.properties
		// Ví dụ: i18n/message_en.properties
		messageResource.setBasename("classpath:i18n/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
//		CookieLocaleResolver resolver = new CookieLocaleResolver();
//		resolver.setCookieDomain("myAppLocaleCookie");
//
//		// 60 phút.
//		resolver.setCookieMaxAge(60 * 60);
		LocaleResolver resolver= new UrlLocaleResolver();
		return resolver;
	}
}
