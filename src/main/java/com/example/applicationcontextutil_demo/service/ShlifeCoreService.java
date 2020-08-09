package com.example.applicationcontextutil_demo.service;

import com.example.applicationcontextutil_demo.Dto.CommonResponseRoot;
import com.example.applicationcontextutil_demo.commonMapper.InterfaceMapper;
import org.springframework.stereotype.Service;

/**
 * @Author LJH
 * @Description 通用调用核心类
 * @Date 16:12 2020/1/7
 * @Param
 * @return
 */

public interface ShlifeCoreService {
    /**
     * @Author LJH
     * @Description 调用核心通用包
     * @Date 16:53 2020/1/7
     * @Param [requestBody, interfaceMapper]
     * @return com.example.applicationcontextutil_demo.Dto.CommonResponseRoot
     */
    CommonResponseRoot callService (Object requestBody, InterfaceMapper interfaceMapper);
    /**
     * @Author LJH
     * @Description 调用核心特例方法
     * @Date 16:54 2020/1/7
     * @Param [requestBody, mapper, needPackage]
     * @return com.example.applicationcontextutil_demo.Dto.CommonResponseRoot
     */
    CommonResponseRoot callService(Object requestBody, InterfaceMapper mapper, boolean needPackage);




    Object revertService(Object requestBody, InterfaceMapper mapper);
}
