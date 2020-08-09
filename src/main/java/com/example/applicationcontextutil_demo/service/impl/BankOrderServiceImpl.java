package com.example.applicationcontextutil_demo.service.impl;

import com.example.applicationcontextutil_demo.service.BankOrderService;
import org.springframework.stereotype.Service;

/**
 * Created by sun on 2019/12/25.
 */
@Service
public class BankOrderServiceImpl implements BankOrderService {
    @Override public int orderbyId(int id) {
        return id +8;
    }
}
