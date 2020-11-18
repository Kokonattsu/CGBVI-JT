package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //指定哪个映射url能跨域 /*单级目录  /** 所有目录
        registry.addMapping("/**")
                //指定哪些网页可以跨域访问
                .allowedOrigins("*")
                //跨域请求是否允许携带凭证（cooke，）
                .allowCredentials(true)
                //凭证超时时间，默认1800 seconds 30分钟
                .maxAge(300)
                //允许跨域的请求方式 默认get、post、head
                .allowedMethods();
    }
}


