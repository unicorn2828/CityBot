package by.kononov.bot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static by.kononov.bot.config.ConfigType.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController(ANY_PAGE)
                .setViewName(HOME);
        registry.addViewController(HOME_PAGE)
                .setViewName(HOME);
        registry.addViewController(BOT_PAGE)
                .setViewName(BOT);
        registry.addViewController(INFO_PAGE)
                .setViewName(INFO);
        registry.addViewController(LOGIN_PAGE)
                .setViewName(LOGIN);
    }
}