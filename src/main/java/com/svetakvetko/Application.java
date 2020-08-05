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

//    @Override
//    public void onStartup(ServletContext container) {
//        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet());
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/webdispatch/*");
//    }
}
/*TODO
   1.Несколько обрабочтиков по разным ссылкам в одном общем контроллере по "теме" (то есть я
   думаю, что придется удалять некоторые контроллеры и группировать оставшиеся)
   2.Разница ModelMap, ModelAndView, ViewResolver = не лучше ли всегда 2-е? пока написала
   так, но я б пообсуждала, стоит ли
   3.Везде удален контекст пас(и в jsp) и я совсем не уверена, что так будет работать, но
   возможно волшебный спринг сможет?
   4.В контролерах в большинстве( или везде) удалила ".jhtml", но в jsp по большей части
   они остались(как и "контекст пас"). По той простой причине, что я не совсем пока понимаю, как именно будет
   работать редирект в контролерах и jsp-шках
   5.В web.xml и здесь есть закоменченный код попыток конфигурирвоать всю эту хуйню. В
   теории он не нужен, однако я оставила на всякий случай.
   6. Создание сессий я пока закомментила в паре мест. Нигде servlet классы не используюся,
   кроме инвалидации сессий, там как бы пока заменить не представляю как
*/


