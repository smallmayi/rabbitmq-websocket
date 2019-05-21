package com.jwis.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 处理前后端分离之后，前端调用后端服务时跨域的问题
 * @author samuel samuelluo@jwis.cn
 *
 */

@SuppressWarnings("deprecation")
@Configuration
public class CorsConfiguration
{

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurerAdapter()
        {
            @Override
            public void addCorsMappings( CorsRegistry registry )
            {
                registry.addMapping( "/**" ).allowedOrigins("*")
                    .allowCredentials(true)
                    .allowedMethods("GET", "POST", "DELETE", "PUT")
                    .maxAge(3600);
            }
        };
    }
}