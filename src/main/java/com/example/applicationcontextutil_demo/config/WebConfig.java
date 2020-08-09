package com.example.applicationcontextutil_demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author LJH
 * @Description 注入 拦截器，过滤器，监听器
 * @Date 15:14 2020/1/10
 * @Param
 * @return
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override //使用addviewController()实现无业务逻辑跳转
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/zxc/foo").setViewName("foo");
    }
    /**
     * @Author LJH
     * @Description 添加拦截器
     * @Date 14:54 2020/1/10
     * @Param [registry]
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(new HandlerInterceptorConfig())
                .addPathPatterns("/asd/**");
    }
    /**
     * @Author LJH
     * @Description 配置过滤器
     * @Date 15:12 2020/1/10
     * @Param []
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new filterConfig());
        frBean.addUrlPatterns("/*");
        System.out.println("filter");
        return frBean;
    }

    /**
     * @Author LJH
     * @Description 配置监听器
     * @Date 15:15 2020/1/10
     * @Param []
     * @return org.springframework.boot.web.servlet.ServletListenerRegistrationBean
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new HttpSessionListenerConfig());
        System.out.println("listener");
        return srb;
    }
}
