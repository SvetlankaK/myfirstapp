package com.svetakvetko;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.svetakvetko.mapper")
@SpringBootApplication
@ServletComponentScan
@MappedTypes({User.class, Role.class})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    /*
    todo 1.я думаю, нужны 2-3(?) проперти файла для ошибок(with the exact filename of ValidationMessages.properties, which can be localized with ValidationMessages_xx_XX.properties files. Those files have to be placed in the root of the classpath.)
     2. пример переопределния дефолтного значения поля:
     В File: messages.properties
     (NotEmpty.customer.name = Name is required!
     Range.customer.age = Age value must be between 1 and 150 )
     */
}



