package by.kononov.bot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandProvider{
    private static final String DEFAULT_VALUE = "default";

    @Autowired
    private CommandType type;

    public Command defineCommand(String commandName){
        Command command = (Command) type.getCommandList()
                                .get(DEFAULT_VALUE);
        if (type.getCommandList()
                .containsKey(commandName)) {
            command = (Command) type.getCommandList()
                                    .get(commandName);
        }
        return command;
    }
}