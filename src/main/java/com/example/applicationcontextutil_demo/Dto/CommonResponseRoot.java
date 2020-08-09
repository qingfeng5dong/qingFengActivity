package com.example.applicationcontextutil_demo.Dto;

import lombok.Data;

import javax.naming.Name;
import javax.ws.rs.HEAD;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sun on 2020/1/7.
 */

//AccessType.PROPERTY: The EJB persistence implementation will load state into your class via JavaBean "setter" methods, and retrieve state from your class using JavaBean "getter" methods. This is the default. 
//             --> 通过getter和setter方法访问Entity的变量，可以把变量定义为private；
//             --> 需要在getter方法上定义字段的属性；
//
//        AccessType.FIELD: State is loaded and retrieved directly from your class' fields. You do not have to write JavaBean "getters" and "setters". 
//            --> 直接访问Entity的变量，可以不定义getter和setter方法，但是需要将变量定义为public；
//            --> 需要在变量上定义字段的属性；

/**
 * @Author LJH
 * @Description 标准返回参数
 * @Date 16:28 2020/1/7
 * @Param
 * @return
 */
@Data
@XmlRootElement(name = "RooT")  // xml 文件的根元素
@XmlAccessorType(XmlAccessType.FIELD)
public class CommonResponseRoot<T> {

    @XmlElement(name = "SYSTEM")
    private Head system;

    @XmlElement(name = "MAIN")
    private T body;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Head{
        private String InterNo;
        private String Result;
        private String Remark;
        private String SubSource;
        private String Time;
    }

}
