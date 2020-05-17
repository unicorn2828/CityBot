package by.kononov.bot.controller;

import by.kononov.bot.command.CommandProvider;
import by.kononov.bot.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/controller")
public class MainController{
    private static final String DEFAULT = "default";
    private static final String PARAM_COMMAND = "command";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_INFO = "info";
    private static final String REDIRECT_PAGE = "redirect:/bot";
    @Autowired
    private CommandProvider commandProvider;

    @GetMapping
    String registration(){
        return REDIRECT_PAGE;
    }

    @PostMapping
    String doCity(@RequestParam Map<String, String> allParams, @Valid City city, BindingResult bindingResult, Model model){
        boolean isBindingResultHasErrors = bindingResult.hasErrors();
        boolean isAuthorized = SecurityContextHolder.getContext()
                                                    .getAuthentication()
                                                    .isAuthenticated();
        if (isBindingResultHasErrors) {
            Map<String, String> error = ControllerUtil.getError(bindingResult);
            model.mergeAttributes(error);
        }
        String command = isAuthorized && !isBindingResultHasErrors ? allParams.get(PARAM_COMMAND) : DEFAULT;
        String page = commandProvider.defineCommand(command)
                                     .execute(allParams.get(PARAM_NAME), allParams.get(PARAM_INFO));
        return page;
    }
}
