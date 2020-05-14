package by.kononov.bot.service.impl;

import by.kononov.bot.entity.City;
import by.kononov.bot.repository.CityRepository;
import by.kononov.bot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService{
    private static final int DEFAULT_CITY_ID = 0;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private City city;

    @Override
    public void addCity(String name, String info){
        city.setId(DEFAULT_CITY_ID);
        city.setName(name);
        city.setInfo(info);
        cityRepository.save(city);
    }

    @Override
    public void updateCity(String name, String info){
        Optional<City> currentCity = cityRepository.findByName(name);
        if (currentCity.isPresent()) {
            city.setId(currentCity.get()
                                  .getId());
            city.setName(name);
            city.setInfo(info);
            cityRepository.save(city);
        }
    }
}