package com.example.applicationcontextutil_demo.commonMapper.impl;

import com.example.applicationcontextutil_demo.Dto.*;
import com.example.applicationcontextutil_demo.commonMapper.*;
import com.example.applicationcontextutil_demo.util.JAXBUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import java.util.Date;

/**
 * Created by sun on 2020/1/7.
 */
@Component("CoreServiceInvoker")
public class CoreServiceInvokerImpl implements CoreServiceInvoker {

    @Value("${gw.endpoint:0}")
    private String endpoint;

    @Value("${gw.endpoint2:2}")
    private String endpoint2;

    @Resource(name = "coreClient")
    private CoreClient coreClient;

    @Override public GWRequest processRequest(Object requestData, InterfaceMapper mapper) {
        ShlifeGwRequest request = new ShlifeGwRequest();
        String requestXml = null;
        try {
            requestXml = JAXBUtils.toXml(requestData.getClass(),null,requestData,"GBK") ;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        request.setErrorFlag(false);
        request.setFunFlag(mapper.getHeadFlag());
        request.setRequestData(requestXml);
        request.setResponseClass(mapper.getResponseBody());
        request.setRequestDesc(mapper.getDesc());
        return  request;
    }

    /**
     * @Author LJH
     * @Description //处理请求信息
     * @Date 17:42 2020/1/7
     * @Param [requestData, mapper, needPackage]
     * @return com.example.applicationcontextutil_demo.commonMapper.GWRequest
     */
    @Override
    public GWRequest processRequest(Object requestData, InterfaceMapper mapper,
                                              boolean needPackage) {
        ShlifeGwRequest request = new ShlifeGwRequest();
        String requestXml = null;
        try {
            if (!needPackage) {
                CommonRequestRoot root = new CommonRequestRoot(mapper.getHeadFlag(),requestData);

                requestXml = JAXBUtils.toXml(root.getClass(),requestData.getClass(),root,"GBK") ;
            } else {
                PackageReq.CommonRequestRoot root= new PackageReq.CommonRequestRoot<>(mapper.getHeadFlag(),requestData);
                PackageReq packageReq = new PackageReq();
                packageReq.setHead(new PackageReq.HeadReq(mapper));
                packageReq.setROOT(root);
                requestXml = JAXBUtils.toXml(packageReq.getClass(),requestData.getClass(),packageReq,"GBK");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setFunFlag(mapper.getHeadFlag());
        request.setRequestData(requestXml);
        request.setResponseClass(mapper.getResponseBody());
        request.setRequestDesc(mapper.getDesc());
        return  request;
    }

    /**
     * @Author LJH
     * @Description //调用核心方法
     * @Date 17:42 2020/1/7
     * @Param [request, needPackage]
     * @return com.example.applicationcontextutil_demo.commonMapper.GWResponse
     */
    @Override
    public GWResponse invoke(GWRequest request, boolean needPackage) {
        ShlifeGwResponse response = new ShlifeGwResponse();
        String responsexml= null;
        if(false) {
            responsexml =coreClient.callLis(request.getRequestData().toString(),endpoint);
        }else {
            responsexml = coreClient.callLis(request.getRequestData().toString(),endpoint2);
        }
        if (StringUtils.isEmpty(responsexml)){
            response.setCallFlag(false);
            response.setCallDesc("调用接口失败");
        } else {
            response.setCallFlag(true);
        }
        response.setResponseData(responsexml);
        response.setResponseDate(new Date());
        response.setRequest(request);
        return response;
    }

    /**
     * @Author LJH
     * @Description //解析返回值信息
     * @Date 17:43 2020/1/7
     * @Param [request, response]
     * @return java.lang.Object
     */
    @Override
    public Object analysisResponse(GWRequest request, GWResponse response) {
        if (!response.getCallFlag()){
            return null;
        }
        try {
            Class meta = ((ShlifeGwRequest)(request)).getResponseClass();
            String responseXml = response.getResponseData().toString();
            CommonResponseRoot responseDto = JAXBUtils.toJavaObject(responseXml, meta);
            ((ShlifeGwResponse)response).setResponseData(response);
            return responseDto;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override public Object analysisResp(GWRequest request, GWResponse response) {
        if (!response.getCallFlag()){
            return null;
        }
        try {
            Class meta = ((ShlifeGwRequest)(request)).getResponseClass();
            String responseXml = response.getResponseData().toString();
            Object Object = JAXBUtils.getJavaObject(responseXml, meta);
            ((ShlifeGwResponse)response).setResponseData(Object);
            return Object;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
