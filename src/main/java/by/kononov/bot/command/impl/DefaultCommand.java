package by.kononov.bot.command.impl;

import by.kononov.bot.command.Command;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DefaultCommand implements Command{
    private static final String PAGE = "bot";

    @Override
    public String execute(String name, String info){
        return PAGE;
    }
}