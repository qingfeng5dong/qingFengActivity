package com.example.applicationcontextutil_demo.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.applicationcontextutil_demo.Dto.PackageReq.DEFAULT_SECOND;

/**
 * Created by sun on 2020/1/13.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@XmlRootElement(name = "Package")
public class SMSRequest implements Serializable {
    public static final String DEFAULT_SECOND = "yyyy-MM-dd HH:mm:ss";
    @XmlElement(name = "Head")
    private HeadReq Head;

    @XmlElement(name = "RequestNode")
    @XmlElementWrapper(name = "RequestNodes")
    private List<RequestNode> RequestNodes;


    public SMSRequest(List<RequestNode> RequestNodes){
        createHead();
        this.RequestNodes = RequestNodes;
    }

    public void  createHead(){
        HeadReq headReq = new HeadReq();
        this.Head =headReq;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static  class HeadReq{

        @XmlElement(name = "TransTime")
        private String TransTime;

        @XmlElement(name = "ThirdPartyCode")
        private String ThirdPartyCode = "15";

        @XmlElement(name = "TransNo")
        private String TransNo;

        @XmlElement(name = "TransType")
        private String TransType;

        @XmlElement(name = "Coworker")
        private String Coworker = "01";
        public HeadReq(){
            this.TransTime = new SimpleDateFormat(DEFAULT_SECOND).format(new Date());
            String aretime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            this.TransNo = aretime + "_" + UUID.randomUUID().toString().replaceAll("-", "");
            this.Coworker = "01";
            this.TransType ="A0034";
            this.ThirdPartyCode ="07";
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static  class RequestNode{
        @XmlElement(name = "UserName")
        private String UserName;

        @XmlElement(name = "IDType")
        private String IDType;

        @XmlElement(name = "IDNo")
        private String IDNo;

        @XmlElement(name = "ContNo")
        private String ContNo;

        @XmlElement(name = "Mobile")
        private String Mobile;



    }

    /**
     * 获得日期字符串
     *
     * @return yyyyMMddHHmmss
     */
    public static String secondStr() {
        return new SimpleDateFormat(DEFAULT_SECOND).format(new Date());
    }


}
