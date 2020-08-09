package com.example.applicationcontextutil_demo.Dto;

import com.example.applicationcontextutil_demo.commonMapper.InterfaceMapper;
import com.example.applicationcontextutil_demo.service.ShlifeInterfaceMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sun on 2020/1/8.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Package")
@NoArgsConstructor
public class PackageReq {
    public static final String DEFAULT_SECOND = "yyyy-MM-dd HH:mm:ss";

    private HeadReq Head;

    private CommonRequestRoot ROOT;


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class HeadReq {
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

        public HeadReq(InterfaceMapper mapper) {
            this.TransTime = new SimpleDateFormat(DEFAULT_SECOND).format(new Date());
            String aretime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            this.TransNo = aretime + "_" + UUID.randomUUID().toString().replaceAll("-", "");
            this.Coworker = "01";
//            if (mapper == ShlifeInterfaceMapper.SK20801103) {
//                this.ThirdPartyCode = "15";
//                this.TransType = "G0001";
//            } else if(mapper == ShlifeInterfaceMapper.SK20801103) {
//                this.ThirdPartyCode = "07";
//                this.TransType = "A0034";
//            }
        }
    }


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CommonRequestRoot<T> {

        @XmlElement(name = "SYSTEM")
        private ChildHead system;

        @XmlElement(name = "MAIN")
        private T body;

        public CommonRequestRoot(String funcFlag, T body) {
            createHead(funcFlag);
            this.body = body;
        }

        public void createHead(String funcFlag) {
            ChildHead system = new ChildHead(funcFlag);
            this.system = system;
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ChildHead {
        @XmlElement(name = "InterNo")
        private String interNo = "";

        public ChildHead(String interNo) {
            this.interNo = interNo;
        }
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
