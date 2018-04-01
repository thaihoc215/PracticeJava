package org.hochnt.springmvcfileud.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("org.hochnt.springmvcfileud.*")
public class ApplicationContextConfig {
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		//Điều hướng prefix đến pages (nơi chưaấ jsp tương ứng)
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	//Định nghĩa bean cho xử lý upload file
	@Bean(name = "multipartResolver")
    public MultipartResolver getMultipartResolver() {
		//Tạo io & dj CommonsMultipartFile duyệt file upload
        CommonsMultipartResolver resover = new CommonsMultipartResolver();
        // 1MB
        resover.setMaxUploadSize(1 * 1024 * 1024);
 
        return resover;
    }
}
