package br.com.alima.alurachallenge3.configuration;

import br.com.alima.alurachallenge3.model.SystemUser;
import br.com.alima.alurachallenge3.repositories.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DBInit {

    @Autowired
    private SystemUserRepository repo;

    @Bean
    public void populateMainUser() {
        SystemUser userAdmin = SystemUser.builder()
                .username("admin@email.com.br")
                .name("Admin")
                .password(new BCryptPasswordEncoder().encode("123999"))
                .enabled(true)
                .build();

        repo.save(userAdmin);
    }

}
