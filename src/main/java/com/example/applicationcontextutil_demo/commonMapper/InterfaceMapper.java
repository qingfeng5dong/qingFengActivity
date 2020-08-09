package com.example.applicationcontextutil_demo.commonMapper;

/**
 * @Author LJH
 * @Description 接口公用描述
 * @Date 16:33 2020/1/7
 * @Param
 * @return
 */
public interface InterfaceMapper {
    /**
     * @Author LJH
     * @Description Class 接口请求Body对象
     * @Date 16:47 2020/1/7
     * @Param []
     * @return java.lang.Class
     */
    Class getRequestBody();


    /**
     * @Author LJH
     * @Description Class 接口返回Body对象 ResponseBody
     * @Date 16:48 2020/1/7
     * @Param []
     * @return java.lang.Class
     */
    Class getResponseBody();

    /**
     * @Author LJH
     * @Description 请求参数接口标记
     * @Date 16:50 2020/1/7
     * @Param []
     * @return java.lang.Class
     */
    String getHeadFlag();


    /**
     * @Author LJH
     * @Description 接口描述
     * @Date 16:51 2020/1/7
     * @Param []
     * @return java.lang.Class
     */
    String getDesc();


}
