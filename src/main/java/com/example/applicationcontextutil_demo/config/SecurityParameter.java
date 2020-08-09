package com.example.applicationcontextutil_demo.config;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @Author LJH
 * @Description 是否加密 默认都加密
 * @Date 17:15 2019/12/26
 * @Param
 * @return
 */

//@Target({ ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Mapping
//@Documented
public @interface SecurityParameter {
    /**
     * 入参是否解密，默认解密
     */
    boolean inDecode() default false;

    /**
     * 出参是否加密，默认加密
     */
    boolean outEncode() default false;
}
