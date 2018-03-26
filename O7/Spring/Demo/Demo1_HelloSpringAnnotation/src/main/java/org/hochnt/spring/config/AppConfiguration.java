package org.hochnt.spring.config;

import org.hochnt.spring.lang.Language;
import org.hochnt.spring.lang.impl.Vietnamese;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
 * là một annotation, 
 * nó được chú thích trên một class, 
 * class này sẽ định nghĩa các Spring BEAN.
 * */
@ComponentScan({"org.hochnt.spring.bean"})
/*
 * Nói cho Spring các package để tìm kiếm các Spring BEAN khác,
 *  Spring sẽ quét (scan) các package đó để tìm kiếm.
 * */
public class AppConfiguration {
 
    @Bean(name ="language")
    public Language getLanguage() {
 
        return new Vietnamese();
    }
      
}
