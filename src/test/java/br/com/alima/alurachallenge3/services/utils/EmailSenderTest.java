package br.com.alima.alurachallenge3.services.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSenderTest {
    @Autowired
    EmailSender sender;

    @Test
    void sendEmail() {
//        EmailSender sender = new EmailSender();
        sender.sendEmail("alessandroslima@hotmail.com", "Password Generator", "Your new password is '123'");
    }
}