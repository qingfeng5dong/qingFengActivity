package com.example.applicationcontextutil_demo.commonMapper;

/**
 * Created by sun on 2020/1/7.
 */
public interface GWRequest {
    /**
     * @Author LJH
     * @Description 请求唯一标识
     * @Date 17:19 2020/1/7
     * @Param []
     * @return java.lang.String
     */

    String getRequestTag();

    void setRequestTag(String requestTag);
    /**
     * @Author LJH
     * @Description 请求描述
     * @Date 17:20 2020/1/7
     * @Param []
     * @return java.lang.String
     */
    String getRequestDesc();

    void  setRequestDesc(String requestDesc);

    /**
     * @Author LJH
     * @Description 请求数据
     * @Date 17:20 2020/1/7
     * @Param []
     * @return java.lang.Object
     */
    Object getRequestData();
    void setRequestData(Object requestData);

    /**
     * @Author LJH
     * @Description 请求错误标记
     * @Date 17:21 2020/1/7
     * @Param []
     * @return java.lang.Boolean
     */
    Boolean getErrorFlag();

    void setErrorFlag(Boolean errorFlag);

    /**
     * @Author LJH
     * @Description 请求错误信息描述
     * @Date 17:22 2020/1/7
     * @Param []
     * @return java.lang.String
     */
    String getErrorMessage();
    void setErrorMessage(String errorMessage);
}
