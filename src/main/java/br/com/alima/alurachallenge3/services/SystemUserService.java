package br.com.alima.alurachallenge3.services;

import br.com.alima.alurachallenge3.model.SystemUser;
import br.com.alima.alurachallenge3.repositories.SystemUserRepository;
import br.com.alima.alurachallenge3.services.exceptions.DataIntegrityViolationException;
import br.com.alima.alurachallenge3.services.exceptions.ObjectNotFoundException;
import br.com.alima.alurachallenge3.services.utils.PasswordGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SystemUserService {

    public static final int PASSWORD_LENGTH = 6;
    private SystemUserRepository repository;

    public List<SystemUser> findAll() {
        return repository.findAll();
    }
    public SystemUser findByUsername(String username) {
        SystemUser user = repository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("E-mail not found!"));
        return user;
    }
    public SystemUser create(SystemUser user) {

        try {
            findByUsername(user.getUsername());
            throw new DataIntegrityViolationException("E-mail already registered.");
        } catch (ObjectNotFoundException ex) {
            user.setEnabled(true);
            String password = PasswordGenerator.generateRandomPassword(PASSWORD_LENGTH);
            System.out.println("Generated Passowrd: " + password);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            return repository.save(user);
        }

    }

}
