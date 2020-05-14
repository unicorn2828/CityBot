package by.kononov.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig{
    private static final String EMAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String EMAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String EMAIL_DEBUG = "mail.debug";
    @Value("${mail.smtp.host}")
    private String host;
    @Value("${mail.smtp.port}")
    private int port;
    @Value("${mail.name}")
    private String name;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.smtp.protocol}")
    private String protocol;
    @Value("${mail.smtp.starttls.enable}")
    private String starttls;
    @Value("${mail.smtp.auth}")
    private String auth;
    @Value("${mail.debug}")
    private String debug;

    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(name);
        mailSender.setPassword(password);
        mailSender.setProtocol(protocol);
        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty(EMAIL_SMTP_STARTTLS, starttls);
        properties.setProperty(EMAIL_SMTP_AUTH, auth);
        properties.setProperty(EMAIL_DEBUG, debug);
        return mailSender;
    }
}