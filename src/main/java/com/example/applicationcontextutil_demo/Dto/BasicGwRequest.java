package com.example.applicationcontextutil_demo.Dto;

import com.example.applicationcontextutil_demo.commonMapper.GWRequest;
import lombok.Data;

import java.util.Date;

/**
 * Created by sun on 2020/1/8.
 */
@Data
public class BasicGwRequest implements GWRequest {

    private String requestTag;

    private String requestDesc;

    private Object requestData;

    private Date requestDate;

    private Boolean errorFlag;

    private String errorMessage;



}
