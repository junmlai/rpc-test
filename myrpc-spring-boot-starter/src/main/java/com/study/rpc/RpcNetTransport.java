package com.study.rpc;

import com.study.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }


    //数据发送
    public Object send(RpcRequest request) throws IOException {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        try {
            //创建连接
            Socket socket = new Socket(host,port);

            //IO操作
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            outputStream.close();
            inputStream.close();
        }
        return null;
    }
}
