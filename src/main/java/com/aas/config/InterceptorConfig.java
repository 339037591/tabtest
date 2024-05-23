package com.aas.config;

import com.aas.config.jwt.AuthorizedInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //实例化对象
    @Bean
    public HandlerInterceptor getInterceptor(){
        return new AuthorizedInterceptor();
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//设置允许跨域的路径
                .allowedOrigins("*")//设置允许跨域请求的域名
                .allowCredentials(true)//是否允许证书 不再默认开启
                .allowedMethods("GET","POST","DELETE","PUT")
                .maxAge(3600);
    }

    //添加拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor())//注册自定义拦截器
                .addPathPatterns("/**")//拦截的请求路径
                .excludePathPatterns("/error")//排除的请求路径
                .excludePathPatterns("/static/*");
    }

}
