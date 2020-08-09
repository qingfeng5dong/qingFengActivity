package com.example.applicationcontextutil_demo.commonMapper;

import com.example.applicationcontextutil_demo.Dto.CommonResponseRoot;

/**
 * @Author LJH
 * @Description 定义一个抽象的业务方法
 * @Date 17:03 2020/1/7
 * @Param
 * @return
 */
public abstract class AbstractCoreService {

    /**
     * @Author LJH
     * @Description 处理核心 返回值信息，调用信息，请求信息接口方法
     * @Date 17:07 2020/1/7
     * @Param
     * @return
     */

    private CoreServiceInvoker invoker;

    protected CommonResponseRoot mainProgress(Object requestBody, InterfaceMapper mapper) {
        return mainProgress(requestBody, mapper, false);
    }

    protected Object revertResponseRoot(Object requestBody, InterfaceMapper mapper) {
        if (mapper == null) {
            throw new IllegalArgumentException("核心请求参数Mapper不能为空");
        }

        if (requestBody != null && !requestBody.getClass().equals(mapper.getRequestBody())) {
            throw new IllegalArgumentException(
                    String.format("核心请求参数与Mapper没有对应,需要类型为[%s]提供的类型为[%s]"
                            , mapper.getRequestBody().getClass(),
                            requestBody.getClass())
            );
        }
        GWRequest request = invoker.processRequest(requestBody, mapper);
        Object result = null;
        if (!request.getErrorFlag()) {
            GWResponse response = invoker.invoke(request, false);
            result = invoker.analysisResp(request, response);
            //            if (openLog != null && openLog) {
            //                saveLogInfo(request, response, mapper);
            //            }
        }

        return  result;
    }

    protected CommonResponseRoot mainProgress(Object requestBody, InterfaceMapper mapper, boolean needPackage) {
        if (mapper == null) {
            throw new IllegalArgumentException("核心请求参数Mapper不能为空");
        }
        if (requestBody != null && !requestBody.getClass().equals(mapper.getRequestBody())) {
            throw new IllegalArgumentException(
                    String.format("核心请求参数与Mapper没有对应,需要类型为[%s]提供的类型为[%s]"
                            , mapper.getRequestBody().getClass(),
                            requestBody.getClass())
            );
        }
        //处理请求报文
        GWRequest request = invoker.processRequest(requestBody, mapper, needPackage);
        Object result = null;
        if (!request.getErrorFlag()) {
            GWResponse response = invoker.invoke(request, true);
            result = invoker.analysisResponse(request, response);
//            if (openLog != null && openLog) {
//                saveLogInfo(request, response, mapper);
//            }
        }
        return (CommonResponseRoot) result;
    }




    public void setInvoker(CoreServiceInvoker invoker) {
        this.invoker = invoker;
    }
}
