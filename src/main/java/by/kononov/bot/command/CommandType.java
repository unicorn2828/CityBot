package by.kononov.bot.command;

import by.kononov.bot.command.impl.AddCityCommand;
import by.kononov.bot.command.impl.DefaultCommand;
import by.kononov.bot.command.impl.RegistrationUserCommand;
import by.kononov.bot.command.impl.UpdateCityCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommandType{
    private static final String ADD_COMMAND = "add";
    private static final String UPDATE_COMMAND = "update";
    private static final String DEFAULT_COMMAND = "default";
    private static final String REGISTRATION_COMMAND = "registration";
    @Autowired
    private ApplicationContext applicationContext;

    public Map getCommandList(){
        Map<String, Command> commandType = new HashMap<>();
        commandType.put(ADD_COMMAND, applicationContext.getBean(AddCityCommand.class));
        commandType.put(UPDATE_COMMAND, applicationContext.getBean(UpdateCityCommand.class));
        commandType.put(DEFAULT_COMMAND, applicationContext.getBean(DefaultCommand.class));
        commandType.put(REGISTRATION_COMMAND, applicationContext.getBean(RegistrationUserCommand.class));
        return commandType;
    }
}