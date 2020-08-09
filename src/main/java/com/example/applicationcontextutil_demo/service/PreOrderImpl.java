package com.example.applicationcontextutil_demo.service;

import org.springframework.stereotype.Service;

/**
 * Created by sun on 2019/12/23.
 */
@Service
public class PreOrderImpl {
    public int orderbyId(int id){

        return (id + 3);
    }
}
