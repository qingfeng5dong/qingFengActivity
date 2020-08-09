package com.example.applicationcontextutil_demo.Dto;

import com.example.applicationcontextutil_demo.commonMapper.GWRequest;
import com.example.applicationcontextutil_demo.commonMapper.GWResponse;
import lombok.Data;

import java.util.Date;

/**
 * Created by sun on 2020/1/8.
 */
@Data
public class BasicGwResponse implements GWResponse {
    private Boolean callFlag;

    private Object responseData;

    private Date responseDate;

    private GWRequest request;

    private String callDesc;


}
