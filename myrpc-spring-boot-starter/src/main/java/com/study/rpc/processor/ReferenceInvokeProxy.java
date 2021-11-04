package com.study.rpc.processor;

import com.study.rpc.RemoteInvokeHandler;
import com.study.rpc.annotation.SelfDefReference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;


public class ReferenceInvokeProxy implements BeanPostProcessor {
    @Autowired
    private RemoteInvokeHandler remoteInvokeHandler;

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(SelfDefReference.class)){
                field.setAccessible(true);

                //创建代理
                Object proxy = Proxy.newProxyInstance(field.getType().getClassLoader(),
                        new Class<?>[]{field.getType()},
                        remoteInvokeHandler);
                try {
                    field.set(bean,proxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return bean;
    }
}
