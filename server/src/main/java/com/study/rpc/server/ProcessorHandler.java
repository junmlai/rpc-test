package com.study.rpc.server;

import com.study.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProcessorHandler implements Runnable{
    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            //反序列化
            RpcRequest request = (RpcRequest) inputStream.readObject();

            //路由
            Mediator mediator = Mediator.getInstance();
            Object result = mediator.processor(request);
            System.out.println("服务端执行结果：" + result);
            outputStream.writeObject(result);
            outputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
