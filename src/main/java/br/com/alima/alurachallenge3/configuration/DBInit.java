package br.com.alima.alurachallenge3.configuration;

import br.com.alima.alurachallenge3.model.SystemUser;
import br.com.alima.alurachallenge3.services.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DBInit {

    @Autowired
    private SystemUserService systemUserService;

    @Bean
    public void populateMainUser() {
        SystemUser userAdmin = SystemUser.builder()
                .email("admin@email.com.br")
                .name("Admin")
                .password(new BCryptPasswordEncoder().encode("123999"))
                .enabled(true)
                .build();

        systemUserService.create(userAdmin);
    }

}
