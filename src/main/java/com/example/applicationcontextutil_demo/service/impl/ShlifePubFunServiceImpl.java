package com.example.applicationcontextutil_demo.service.impl;

import com.example.applicationcontextutil_demo.Dto.CommonResponseRoot;
import com.example.applicationcontextutil_demo.commonMapper.AbstractCoreService;
import com.example.applicationcontextutil_demo.commonMapper.CoreServiceInvoker;
import com.example.applicationcontextutil_demo.commonMapper.InterfaceMapper;
import com.example.applicationcontextutil_demo.service.ShlifeCoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.Name;

/**
 * Created by sun on 2020/1/7.
 */
@Service
public class ShlifePubFunServiceImpl extends AbstractCoreService implements ShlifeCoreService {


    @Resource(name = "CoreServiceInvoker")
    public void setInvoker(CoreServiceInvoker invoker){

        super.setInvoker(invoker);
    }


    /**
     * @Author LJH
     * @Description 公用方法
     * @Date 15:56 2020/1/8
     * @Param [requestBody, interfaceMapper]
     * @return com.example.applicationcontextutil_demo.Dto.CommonResponseRoot
     */
    @Override public CommonResponseRoot callService(Object requestBody,
                                                    InterfaceMapper interfaceMapper) {
        return mainProgress(requestBody,interfaceMapper);
    }

    /**
     * @Author LJH
     * @Description //特殊字段构造方法
     * @Date 15:57 2020/1/8
     * @Param [requestBody, mapper, needPackage]
     * @return com.example.applicationcontextutil_demo.Dto.CommonResponseRoot
     */
    @Override public CommonResponseRoot callService(Object requestBody, InterfaceMapper mapper,
                                                    boolean needPackage) {
        return mainProgress(requestBody,mapper,needPackage);
    }

    @Override public Object revertService(Object requestBody, InterfaceMapper mapper) {
        return revertResponseRoot(requestBody,mapper);
    }
}
