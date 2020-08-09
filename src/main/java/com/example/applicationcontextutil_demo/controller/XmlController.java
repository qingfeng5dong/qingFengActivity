package com.example.applicationcontextutil_demo.controller;

import com.example.applicationcontextutil_demo.Dto.SMSRequest;
import com.example.applicationcontextutil_demo.service.ShlifeCoreService;
import com.example.applicationcontextutil_demo.service.ShlifeInterfaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2020/1/13.
 */

@RestController
public class XmlController {
    @Autowired ShlifeCoreService shlifeCoreService;


    @GetMapping("/xml")
    public void  sms(SMSRequest smsRequest){
        SMSRequest.RequestNode requestNode = new SMSRequest.RequestNode();
        requestNode.setContNo("");
        requestNode.setIDNo("3600117101288796");
        requestNode.setMobile("15645636547");
        requestNode.setUserName("周一一");
        requestNode.setIDType("2");
        List<SMSRequest.RequestNode> RequestNodes = new ArrayList<>();
        RequestNodes.add(requestNode);
        SMSRequest smsRequest1 = new SMSRequest(RequestNodes);
        shlifeCoreService.revertService(smsRequest1, ShlifeInterfaceMapper.A0034);
    }
}
