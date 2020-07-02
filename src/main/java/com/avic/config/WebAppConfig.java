package com.avic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	AuthenticationInterceptor interceptorConfig;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册自定义拦截器，添加拦截路径和排除拦截路径
		registry.addInterceptor(interceptorConfig)
				.addPathPatterns("/admin/**")
				.addPathPatterns("/api/**")
				.excludePathPatterns("/admin/login.html")
				.excludePathPatterns("/admin/dWF2X2xvZ2lu");
	}
}