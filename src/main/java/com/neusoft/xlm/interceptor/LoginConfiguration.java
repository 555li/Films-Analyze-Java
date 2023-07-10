package com.neusoft.xlm.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/templates/**", "/js/**",
                        "/assets/**", "/css/**", "/errorPageImg/**",
                        "/img/**", "/lib/**", "/MaskPicture/**"
                        )
                .excludePathPatterns("/login","/loginCode","/adminLogin","/registry","/sendCode","/loginByCode","/register/**")
                .excludePathPatterns("/index","/admin");
    }
}
