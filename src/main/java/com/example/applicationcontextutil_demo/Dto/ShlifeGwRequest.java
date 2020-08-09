package com.example.applicationcontextutil_demo.Dto;

import lombok.Data;

/**
 * Created by sun on 2020/1/8.
 */
@Data
public class ShlifeGwRequest extends BasicGwRequest {

    private String funFlag;

    private Class responseClass;

    private String funName;

    private Object requestObject;
}
