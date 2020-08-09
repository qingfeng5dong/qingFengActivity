package com.example.applicationcontextutil_demo.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author LJH
 * @Description 拦截器配置
 * @Date 15:25 2020/1/10
 * @Param
 * @return
 */
public class HandlerInterceptorConfig implements HandlerInterceptor {

    /**
     * 进入controller方法之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginIntercepter------->preHandle");

        System.out.println("preHandle被调用");
        Map map =(Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        System.out.println(map.get("name"));
        System.out.println(request.getParameter("username"));
        if(map.get("name").equals("zhangsan")) {
            return true;    //如果false，停止流程，api被拦截
        }else {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("please login again!");
            return false;
        }
    }

    /**
     * 调用完controller之后，视图渲染之前
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("LoginIntercepter------->postHandle");

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 整个完成之后，通常用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("LoginIntercepter------->afterCompletion");

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


}
