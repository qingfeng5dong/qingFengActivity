package com.example.applicationcontextutil_demo.Dto;

import jdk.internal.dynalink.beans.StaticClass;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sun on 2020/1/8.
 */
@Data
@XmlRootElement(name = "RooT")
@NoArgsConstructor// ： 生成一个无参数的构造方法
@XmlAccessorType(XmlAccessType.FIELD)
public class CommonRequestRoot<T> {

    @XmlElement(name = "SYSTEM")
    private Head system;

    @XmlElement(name = "MAIN")
    private T body;

    public CommonRequestRoot(String funcFlag, T body) {
        this.body =body;
        createHead(funcFlag);
    }

    public void createHead(String funcFlag){
        Head head =new Head(funcFlag);
        this.system = head;
    }


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static  class Head{

        @XmlElement(name = "InterNo")
        private String interNo = "";

        public Head (String interNo){
           this.interNo = interNo;
        }




    }
}
