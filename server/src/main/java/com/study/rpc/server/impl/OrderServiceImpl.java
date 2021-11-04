package com.study.rpc.server.impl;

import com.study.rpc.IOrderService;
import com.study.rpc.server.annotation.SelfDefRemoteService;
import org.springframework.stereotype.Service;

@SelfDefRemoteService
public class OrderServiceImpl implements IOrderService {
    public String getOrderPrice(String orderId) {
        return "Price for order: $10";
    }

    public String getOrderInfo(String orderId) {
        return "id:1234, price:$10";
    }
}
