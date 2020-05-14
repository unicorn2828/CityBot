package by.kononov.bot.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender{
    private static final String EMAIL_SENDER = "CityBot";
    @Value("${mail.subject}")
    private String subject;
    @Value("${mail.body}")
    private String body;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String emailTo){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(EMAIL_SENDER);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
    }
}