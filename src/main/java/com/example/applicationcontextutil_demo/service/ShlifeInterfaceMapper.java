package com.example.applicationcontextutil_demo.service;

import com.example.applicationcontextutil_demo.Dto.SMSRequest;
import com.example.applicationcontextutil_demo.Dto.SMSResponse;
import com.example.applicationcontextutil_demo.commonMapper.InterfaceMapper;

/**
 * Created by za-tangchuanbo
 * 2018/9/10 18:17
 */
public enum ShlifeInterfaceMapper implements InterfaceMapper {

    A0034(SMSRequest.class, SMSResponse.class, "sk20801103", "根据手机号查询个人理赔保单");
    private Class requestBody;

    private Class responseBody;

    private String headFlag;

    private String desc;

    ShlifeInterfaceMapper(Class requestBody, Class responseBody, String headFlag, String desc) {
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.headFlag = headFlag;
        this.desc = desc;
    }

    @Override
    public Class getRequestBody() {
        return requestBody;
    }

    @Override
    public Class getResponseBody() {
        return responseBody;
    }

    @Override
    public String getHeadFlag() {
        return headFlag;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public static ShlifeInterfaceMapper getMapper(String funFlag) {
        for (ShlifeInterfaceMapper mapper : values()) {
            if (mapper.getHeadFlag().equals(funFlag)) {
                return mapper;
            }
        }
        return null;
    }
}
