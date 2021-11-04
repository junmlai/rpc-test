package com.study.rpc.server;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
//spring 容器启动完成之后，会发布一个ContextRefreshedEvent=
public class SocketServerInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final ExecutorService executorService= Executors.newCachedThreadPool();

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ServerSocket serverSocket=null;

        try {
            serverSocket=new ServerSocket(8888);
            System.out.println("服务启动了，等待连接。。。。。");
            while(true){
                Socket socket=serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
