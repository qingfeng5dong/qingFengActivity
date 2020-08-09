package com.example.applicationcontextutil_demo.commonMapper;

/**
 * Created by sun on 2020/1/7.
 */
public interface CoreServiceInvoker {


    GWRequest processRequest(Object requestData, InterfaceMapper mapper);

    /**
     * 处理请求信息
     */
    GWRequest processRequest(Object requestData, InterfaceMapper mapper, boolean needPackage);

    /**
     * 调用核心方法
     */
    GWResponse invoke(GWRequest request, boolean needPackage);

    /**
     * 处理返回信息
     */
    Object analysisResponse(GWRequest request, GWResponse response);
    Object analysisResp(GWRequest request, GWResponse response);
}
