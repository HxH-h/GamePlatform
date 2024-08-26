package com.game.Configuration;

import com.game.Controller.Interceptor.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCconfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePatterns = new String[]{"/swagger-resources/**", "/webjars/**", "/v3/**", "/swagger-ui.html/**",
                "/api", "/api-docs", "/api-docs/**", "/doc.html/**","/favicon.ico","/player/login","/player/register","/player/genCode/**"};
        registry.addInterceptor(new LoginIntercepter())
                .addPathPatterns("/**").excludePathPatterns(excludePatterns);

    }
}
