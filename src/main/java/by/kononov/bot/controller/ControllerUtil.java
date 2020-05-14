package by.kononov.bot.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class ControllerUtil{
    static Map<String, String> getError(BindingResult bindingResult){
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(fieldError -> fieldError.getField(), FieldError::getDefaultMessage);
        return bindingResult.getFieldErrors()
                            .stream()
                            .collect(collector);
    }
}
