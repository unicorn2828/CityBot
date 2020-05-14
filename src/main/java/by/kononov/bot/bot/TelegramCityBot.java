package by.kononov.bot.bot;

import by.kononov.bot.entity.City;
import by.kononov.bot.repository.CityRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class TelegramCityBot extends TelegramLongPollingBot{
    static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_MESSAGE = "city not found";
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.name}")
    private String botUserName;
    @Autowired
    private CityRepository cityRepository;

    static{
        ApiContextInitializer.init();
    }

    @Override
    public void onUpdateReceived(Update update){
        update.getUpdateId();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage()
                                                                    .getChatId());
        String cityName = update.getMessage()
                                .getText()
                                .toLowerCase();
        Optional<City> city = cityRepository.findByName(cityName);
        String message = city.isPresent() ? city.get()
                                                .getInfo() : DEFAULT_MESSAGE;
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("send a message failed ", e);
        }
    }

    @Override
    public String getBotUsername(){
        return botUserName;
    }

    @Override
    public String getBotToken(){
        return botToken;
    }

    @PostConstruct
    private void botInit(){
        TelegramBotsApi telegram = new TelegramBotsApi();
        try {
            telegram.registerBot(this);
        } catch (TelegramApiRequestException e) {
            logger.error("register bot failed ", e);
        }
    }
}