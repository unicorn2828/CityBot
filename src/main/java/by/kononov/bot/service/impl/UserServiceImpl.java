package by.kononov.bot.service.impl;

import by.kononov.bot.entity.Role;
import by.kononov.bot.entity.User;
import by.kononov.bot.repository.UserRepository;
import by.kononov.bot.mail.MailSender;
import by.kononov.bot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private static final int DEFAULT_USER_ID = 0;
    private static final boolean DEFAULT_USER_ACTIVE = true;
    private static final Set<Role> DEFAULT_USER_ROLE = Collections.singleton(Role.USER);
    @Autowired
    private User user;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean registrationUser(String userName, String password){
        boolean isUserPresent = userRepository.findByUsername(userName)
                                              .isPresent();
        if (!isUserPresent) {
            user.setUsername(userName);
            user.setPassword(password);
            user.setId(DEFAULT_USER_ID);
            user.setActive(DEFAULT_USER_ACTIVE);
            user.setRoles(DEFAULT_USER_ROLE);
            userRepository.save(user);
            mailSender.sendEmail(user.getEmail());
        }
        return isUserPresent;
    }
}