package br.com.alima.alurachallenge3.services;

import br.com.alima.alurachallenge3.repositories.SystemUserRepository;
import br.com.alima.alurachallenge3.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private SystemUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found!"));
    }
}
