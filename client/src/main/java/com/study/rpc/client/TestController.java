package com.study.rpc.client;

import com.study.rpc.annotation.SelfDefReference;
import com.study.rpc.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @SelfDefReference
    private IOrderService orderService;

    @GetMapping("/test")
    public String test(){
        return orderService.getOrderInfo("12345");
    }
}
