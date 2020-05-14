package by.kononov.bot.controller;

import by.kononov.bot.command.CommandProvider;
import by.kononov.bot.encoder.CustomEncoder;
import by.kononov.bot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController{
    private static final String REGISTRATION = "registration";
    private static final String ATTRIBUTE_MESSAGE = "message";
    private static final String MESSAGE_TEXT = " is already exists!";
    @Autowired
    private CommandProvider commandProvider;
    @Autowired
    private CustomEncoder customEncoder;
    @Autowired
    private User user;

    @GetMapping
    public String registration(){
        return REGISTRATION;
    }

    @PostMapping
    public String addUser(User currentUser, Model model){
        String userName = currentUser.getUsername();
        String userPassword = customEncoder.receivePasswordEncoder()
                                           .encode(currentUser.getPassword());
        user.setEmail(currentUser.getEmail());
        String page = commandProvider.defineCommand(REGISTRATION)
                                     .execute(userName, userPassword);
        if (page.equals(REGISTRATION)) {
            model.addAttribute(ATTRIBUTE_MESSAGE, userName + MESSAGE_TEXT);
        }
        return page;
    }
}