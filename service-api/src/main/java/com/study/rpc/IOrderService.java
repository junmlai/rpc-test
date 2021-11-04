package com.study.rpc;

public interface IOrderService {
    String getOrderPrice(String orderId);
    String getOrderInfo(String orderId);
}
