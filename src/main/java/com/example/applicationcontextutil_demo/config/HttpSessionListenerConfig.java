package com.example.applicationcontextutil_demo.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author LJH
 * @Description 监听器
 * @Date 15:06 2020/1/10
 * @Param
 * @return
 */
public class HttpSessionListenerConfig implements HttpSessionListener {
    public static int online = 0;

    /**
     * @Author LJH
     * @Description 创建session、调用此方法
     *
     * @Date 15:11 2020/1/10
     * @Param [se]
     * @return void
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        online ++;
    }

    /**
     * @Author LJH
     * @Description 销毁session调用此方法
     * @Date 15:11 2020/1/10
     * @Param [se]
     * @return void
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");

    }
}
