package com.svetakvetko;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

@MapperScan("com.svetakvetko.mapper")
@SpringBootApplication
@ServletComponentScan
@MappedTypes({User.class, Role.class})
public class Application extends SpringBootServletInitializer {
    @Autowired
    private static PasswordEncoder passwordEncoder;


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}



