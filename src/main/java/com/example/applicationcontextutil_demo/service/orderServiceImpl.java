package com.example.applicationcontextutil_demo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Created by sun on 2019/12/25.
 */
@Service
public class orderServiceImpl {
    private ExecutorService executor = Executors.newFixedThreadPool(2);;

    /**
     * @Author LJH
     * @Description 开启线程返回结果
     * @Date 17:39 2019/12/25
     * @Param []
     * @return com.example.applicationcontextutil_demo.service.Order
     */
    public Order submit () {

        Future future =executor.submit(new Callable<Order>() {

            @Override public Order call() throws Exception {
                Order order = new Order();
                order.setBank("你好");
                order.setId("123");
                order.setName("年后我来了");
                return order;
            }
        });

        try {
            return (Order) future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void submit1 () {
        Future future =executor.submit(new Runnable() {
            @Override public void run() {
                System.out.println("你好我来了");
            }
        });

    }







}
