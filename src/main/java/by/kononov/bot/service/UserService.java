package by.kononov.bot.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService{

    boolean registrationUser(String userName, String password);
}
