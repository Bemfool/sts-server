package bgroup.stocktradingsystem.stsserver.config;

import bgroup.stocktradingsystem.stsserver.interceptor.AdminLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    AdminLoginInterceptor getAdminLoginInterceptor() {
        return new AdminLoginInterceptor();
    }

    @Override
    public void  addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addAdminInterceptor = registry.addInterceptor(getAdminLoginInterceptor());
//        addAdminInterceptor.addPathPatterns("/admin/**");
//        addAdminInterceptor.excludePathPatterns("/error/**");
//        addAdminInterceptor.excludePathPatterns("/admin/login");
//        InterceptorRegistration addClientInterceptor = registry.addInterceptor(getAdminLoginInterceptor());
//        addClientInterceptor.addPathPatterns("/client/**");
//        addClientInterceptor.excludePathPatterns("/error/**");
//        addClientInterceptor.excludePathPatterns("/client/login");
    }
}
