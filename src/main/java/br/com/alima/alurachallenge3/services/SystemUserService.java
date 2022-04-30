package br.com.alima.alurachallenge3.services;

import br.com.alima.alurachallenge3.model.SystemUser;
import br.com.alima.alurachallenge3.repositories.SystemUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SystemUserService {

    private SystemUserRepository repository;

    public SystemUser create(SystemUser user) {
        return repository.save(user);
    }

}
