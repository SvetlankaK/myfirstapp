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
    todo 1.не ебу пока что как конретно привязывать валидацию контроллера к конкретному
    интерфейсу валдиации. в примерах люди как-то так извраались, да ещё и у них там всякие
    реквесты были, так шо я пока не ебу
     */
}



