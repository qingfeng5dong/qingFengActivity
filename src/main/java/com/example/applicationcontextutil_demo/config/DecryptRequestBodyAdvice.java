package com.example.applicationcontextutil_demo.config;

import com.alibaba.fastjson.JSON;
import com.example.applicationcontextutil_demo.util.DesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Author LJH
 * @Description 对请求进行解密操作
 * @Date 15:29 2019/12/26
 * @Param
 * @return
 */
@Slf4j
@RestControllerAdvice
@Component
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    @Override public boolean supports(MethodParameter methodParameter, Type type,
                                      Class<? extends HttpMessageConverter<?>> aClass) {
        //这里设置成false 它就不会再走这个类了
       return methodParameter.hasParameterAnnotation(Encrypt.class);
       // return methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class);
    }

    @Override public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage,
                                                     MethodParameter methodParameter, Type type,
                                                     Class<? extends HttpMessageConverter<?>> aClass)
            throws IOException {
        return httpInputMessage;
    }

    @Override public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage,
                                          MethodParameter methodParameter, Type type,
                                          Class<? extends HttpMessageConverter<?>> aClass) {
        String dealData = null;
        try {
            //解密操作
            Map<String,String> dataMap = (Map)o;
            log.info("接收到原始请求数据={}", JSON.toJSONString(dataMap));
            String srcData = dataMap.get("data");
            dealData = DesUtil.decrypt(srcData);
            log.info("解密后数据={}",dealData);
        } catch (Exception e) {
            log.error("异常！", e);
        }
        return dealData;
    }

    @Override public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage,
                                            MethodParameter methodParameter, Type type,
                                            Class<? extends HttpMessageConverter<?>> aClass) {
        return null;
    }
}
