package com.jt.config;

import com.jt.intercept.UserIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer{
	
	//开启匹配后缀型配置
	// (拦截以.html为结尾的请求，去掉后缀，以restful的的结构)
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {

		configurer.setUseSuffixPatternMatch(true);

	}

	@Autowired
	private UserIntercept userIntercept;
	//添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userIntercept)
				.addPathPatterns("/cart/**","/order/**");
	}
}
