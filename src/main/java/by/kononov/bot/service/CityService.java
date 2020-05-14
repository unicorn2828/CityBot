package by.kononov.bot.service;

import org.springframework.stereotype.Service;

@Service
public interface CityService{

    void addCity(String name, String info);

    void updateCity(String name, String info);
}
