package com.yarikonen.web44.Filters;

import com.yarikonen.web44.Services.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Autowired
    @Bean
    public FilterRegistrationBean<SecureFilter> getSecureFilter(SecretService secretService){
        FilterRegistrationBean<SecureFilter> filterRegistrationBean= new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new SecureFilter(secretService));
        filterRegistrationBean.addUrlPatterns("/api/dots/*");
        return filterRegistrationBean;
    }
}
