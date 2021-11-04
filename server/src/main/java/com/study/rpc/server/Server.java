package com.study.rpc.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Configuration
@ComponentScan(basePackages = "com.study.rpc.server")
public class Server {
    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext=
                new AnnotationConfigApplicationContext(Server.class);
    }
}
