package by.kononov.bot.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomEncoder{

    public PasswordEncoder receivePasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}