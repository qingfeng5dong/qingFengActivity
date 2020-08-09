package com.example.applicationcontextutil_demo.controller;

import com.example.applicationcontextutil_demo.enums.OrderType;
import com.example.applicationcontextutil_demo.service.Order;
import com.example.applicationcontextutil_demo.service.OrderFactory;
import com.example.applicationcontextutil_demo.service.TestServiceImpl;
import com.example.applicationcontextutil_demo.util.ApplicationContextUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        //注意getBean(String s) bean在spring中对象名小写开头
        return ((TestServiceImpl)ApplicationContextUtil.getBean("testServiceImpl")).hello();
    }

    @RequestMapping("/test2")
    public String test2(){
        return ApplicationContextUtil.getBean(TestServiceImpl.class).hello();
    }

    @GetMapping("/test3")
    public int test3(@RequestParam("type") int type){
       String name = OrderType.getClassName(type);
        try {
         return OrderFactory.creatInstance(name,type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @GetMapping("/test4")
    public Order test3(@RequestParam("name") String methodName, @RequestParam("type") int type){
        String name = OrderType.getClassName(type);
        try {
            return OrderFactory.creatInstances(name,methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @GetMapping("/test5")
    public void test5(@RequestParam("name") String methodName, @RequestParam("type") int type){
        String name = OrderType.getClassName(type);
        try {
           OrderFactory.creatInstancet(name,methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
