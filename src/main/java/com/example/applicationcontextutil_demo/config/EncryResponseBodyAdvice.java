package com.example.applicationcontextutil_demo.config;

import com.alibaba.fastjson.JSON;
import com.example.applicationcontextutil_demo.util.DesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.AnnotatedElement;

/**
 * @Author LJH
 * @Description 加密数据
 * @Date 16:08 2019/12/26
 * @Param
 * @return
 */
@Component
@RestControllerAdvice
@Slf4j
public class EncryResponseBodyAdvice implements ResponseBodyAdvice{
    @Override public boolean supports(MethodParameter methodParameter, Class aClass) {
        //这里设置成false 它就不会再走这个类了
       // System.out.println(methodParameter.hasMethodAnnotation(Encrypt.class));
        //return  methodParameter.hasMethodAnnotation(ResponseBody.class);
       // return methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class);
        /**
         * 在这个方法完成 "是否对这个接口生效"  方案1.直接判断方法名 方案二2.判断哪些接口打注解[推荐]
         */
//        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
//        Encrypt focusController = AnnotationUtils
//                .findAnnotation(annotatedElement, Encrypt.class);
//        return focusController != null;
        return  methodParameter.hasMethodAnnotation(Encrypt.class);


    }

    @Override public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                            MediaType mediaType, Class aClass,
                                            ServerHttpRequest serverHttpRequest,
                                            ServerHttpResponse serverHttpResponse) {
        //通过 ServerHttpRequest的实现类ServletServerHttpRequest 获得HttpServletRequest
        ServletServerHttpRequest sshr = (ServletServerHttpRequest) serverHttpRequest;
        //此处获取到request 是为了取到在拦截器里面设置的一个对象 是我项目需要,可以忽略
        HttpServletRequest request = sshr.getServletRequest();

        String returnStr = "";

        try {
            //添加encry header，告诉前端数据已加密
            serverHttpResponse.getHeaders().add("encry", "true");
            String srcData = JSON.toJSONString(o);
            //加密
            returnStr = DesUtil.encrypt(srcData);
            log.info("接口={},原始数据={},加密后数据={}", request.getRequestURI(), srcData, returnStr);

        } catch (Exception e) {
            log.error("异常！", e);
        }
        return returnStr;
    }
}
