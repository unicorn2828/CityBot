package by.kononov.bot.command.impl;

import by.kononov.bot.command.Command;
import by.kononov.bot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCityCommand implements Command{
    private static final String PAGE = "bot";
    @Autowired
    private CityService cityService;

    @Override
    public String execute(String name, String info){
        cityService.updateCity(name, info);
        return PAGE;
    }
}
