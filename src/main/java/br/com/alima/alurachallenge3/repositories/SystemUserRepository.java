package br.com.alima.alurachallenge3.repositories;

import br.com.alima.alurachallenge3.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {

    Optional<SystemUser> findByUsername(String username);

}
