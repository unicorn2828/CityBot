package by.kononov.bot.command.impl;

import by.kononov.bot.command.Command;
import by.kononov.bot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationUserCommand implements Command{
    private static final String REGISTRATION_PAGE = "registration";
    private static final String REDIRECT_PAGE = "redirect:/login";
    @Autowired
    private UserService userService;

    @Override
    public String execute(String userName, String password){
        return userService.registrationUser(userName, password) ? REGISTRATION_PAGE : REDIRECT_PAGE;
    }
}