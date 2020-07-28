package com.svetakvetko;

import com.svetakvetko.domain.Role;
import com.svetakvetko.domain.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan
@SpringBootApplication
@ServletComponentScan
@MappedTypes({User.class, Role.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}