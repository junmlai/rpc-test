package com.study.rpc;

import com.study.rpc.RpcNetTransport;
import com.study.rpc.RpcRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvokeHandler implements InvocationHandler {
    @Value("${remote.server.host}")
    private String host;
    @Value("${remote.server.port}")
    private int port;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcNetTransport transport = new RpcNetTransport(host, port);

        RpcRequest request = new RpcRequest();
//        request.setClassName(proxy.getClass().getName());
        request.setClassName(method.getDeclaringClass().getName());

        request.setArgs(args);
        request.setMethodName(method.getName());
        request.setTypes(method.getParameterTypes());

        return transport.send(request);
    }
}
