package com.example.applicationcontextutil_demo.commonMapper;

import java.util.Date;

/**
 * Created by sun on 2020/1/7.
 */
public interface GWResponse {


    /**
     * @Author LJH
     * @Description 返回标记
     * @Date 17:34 2020/1/7
     * @Param []
     * @return java.lang.Boolean
     */
    public Boolean getCallFlag() ;

    public void setCallFlag(Boolean callFlag) ;



    /**
     * @Author LJH
     * @Description 返回值
     * @Date 17:35 2020/1/7
     * @Param []
     * @return java.lang.Object
     */
    public Object getResponseData() ;

    public void setResponseData(Object responseData);

    /**
     * @Author LJH
     * @Description 返回时间标记
     * @Date 17:35 2020/1/7
     * @Param []
     * @return java.util.Date
     */
    public Date getResponseDate();

    public void setResponseDate(Date responseDate) ;


    /**
     * @Author LJH
     * @Description 请求参数
     * @Date 17:35 2020/1/7
     * @Param []
     * @return com.example.applicationcontextutil_demo.commonMapper.GWRequest
     */



    GWRequest getRequest();

    void setRequest(GWRequest request);


    /**
     * @Author LJH
     * @Description 返回描述
     * @Date 17:36 2020/1/7
     * @Param []
     * @return java.lang.String
     */
    public String getCallDesc() ;

    public void setCallDesc(String callDesc) ;
}
