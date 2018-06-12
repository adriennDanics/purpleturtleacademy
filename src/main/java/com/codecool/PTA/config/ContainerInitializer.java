package com.codecool.PTA.config;

import com.codecool.PTA.model.Container;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContainerInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        Container container = new Container();
        container.createObjects(sce);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

}