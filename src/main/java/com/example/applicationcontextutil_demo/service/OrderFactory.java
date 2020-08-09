package com.example.applicationcontextutil_demo.service;

import com.example.applicationcontextutil_demo.util.ApplicationContextUtil;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by sun on 2019/12/23.
 */
@Service
public class OrderFactory {
    /**
     * 创建新的对象
     *
     * @param classname 创建的类名称
     * @param
     * @return
     */
    public static int creatInstance(String classname, int id)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj = ApplicationContextUtil.getBean(classname);

        Method mo = obj.getClass().getMethod("orderbyId",int.class);

        int o = (int) mo.invoke(obj,id);

        return o;
    }

    public static Order creatInstances(String classname,String methodName)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj = ApplicationContextUtil.getBean(classname);

        Method mo = obj.getClass().getMethod(methodName);

        Order order = (Order) mo.invoke(obj);

        return order;
    }


    public static void creatInstancet(String classname,String methodName)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj = ApplicationContextUtil.getBean(classname);

        Method mo = obj.getClass().getMethod(methodName);

        mo.invoke(obj);


    }
}
