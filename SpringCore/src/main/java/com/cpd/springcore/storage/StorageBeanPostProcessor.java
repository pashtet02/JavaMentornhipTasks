package com.cpd.springcore.storage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class StorageBeanPostProcessor implements BeanPostProcessor {
    @Value("${datafile.path}")
    private String filepath;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
