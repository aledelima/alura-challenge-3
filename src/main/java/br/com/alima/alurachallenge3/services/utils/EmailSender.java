package br.com.alima.alurachallenge3.services.utils;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String toEmail, String subject, String body) {

        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("admin@email.com.br");
            msg.setTo(toEmail);
            msg.setSubject(subject);
            msg.setText(body);
            mailSender.send(msg);
        } catch (MailException ex) {
            ex.printStackTrace();
        }
    }

}