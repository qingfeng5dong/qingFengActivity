package com.example.applicationcontextutil_demo.config;

import org.apache.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author LJH
 * @Description 过滤器实现
 * @Date 14:11 2020/1/10
 * @Param
 * @return
 */
public class filterConfig implements Filter {
    /**
     * @Author LJH
     * @Description 容器加载时调用
     * @Date 14:09 2020/1/10
     * @Param [filterConfig]
     * @return void
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("拦截器进入========拦截器进入========");
    }


    /**
     * @Author LJH
     * @Description 请求被拦截时调用
     * @Date 14:11 2020/1/10
     * @Param [servletRequest, servletResponse, filterChain]
     * @return void
     */
    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                                   FilterChain filterChain) throws IOException, ServletException {

        System.out.println(servletRequest.getParameter("name"));
        //获取requet
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getRequestURI().indexOf("/login"));
        if (request.getRequestURI().indexOf("/index") != -1 ||
           request.getRequestURI().indexOf("/asd") != -1   ||
           request.getRequestURI().indexOf("/online") !=-1 ||
           request.getRequestURI().indexOf("/login") != -1 ){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            response.sendRedirect("/login");
        }

    }

    /**
     * @Author LJH
     * @Description 容器被销毁时调用
     * @Date 14:12 2020/1/10
     * @Param []
     * @return void
     */
    @Override
    public void destroy() {
        System.out.println("拦截器销毁========拦截器销毁========");
    }
}
