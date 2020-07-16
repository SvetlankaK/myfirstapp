package com.svetakvetko.config;

import org.apache.catalina.startup.Tomcat;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.File;

@Configuration
public class AppConfig {
    @Bean
    public TomcatServletWebServerFactory servletContainerFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                new File(tomcat.getServer().getCatalinaBase(), "webapps").mkdirs();
                tomcat.addWebapp("/webdispatch", "C:\\Users\\Alex\\IdeaProjects\\myfirstapp\\out\\artifacts\\myfirstapp\\myfirstapp.war");
                return super.getTomcatWebServer(tomcat);
            }
        };
    }
}
