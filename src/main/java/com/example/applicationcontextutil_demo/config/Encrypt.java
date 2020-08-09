package com.example.applicationcontextutil_demo.config;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 进行参数加密和解密
 *
 * @author huan.fu
 * @date 2018/9/28 - 16:08
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
//@Target({ ElementType.PARAMETER, ElementType.METHOD })
//@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping

public @interface Encrypt {
}
