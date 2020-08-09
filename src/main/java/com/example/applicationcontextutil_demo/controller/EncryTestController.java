package com.example.applicationcontextutil_demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.applicationcontextutil_demo.config.Encrypt;
import com.example.applicationcontextutil_demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by sun on 2019/12/26.
 */
@RestController
@RequestMapping("/encry")
@Slf4j
public class EncryTestController {

    @RequestMapping(value = "/send")

    public  Result sendResponseEncryData() {
        Result result = Result.createResult().setSuccess(true);
        result.setDataValue("name", "Java碎碎念");
        result.setDataValue("encry", true);
        return result;
    }

    /**
     * 获取 解密后的 请求参数
     */
    @RequestMapping(value = "/get")
    @Encrypt
    public Result getRequestData(@RequestBody @Encrypt  Object object) {
        log.info("controller接收的参数object={}", object.toString());
        Result result = Result.createResult().setSuccess(true);
        result.setData((Map)JSON.parse(object.toString()));
        return result;
    }
}
