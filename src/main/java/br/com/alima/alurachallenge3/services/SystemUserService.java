package br.com.alima.alurachallenge3.services;

import br.com.alima.alurachallenge3.model.SystemUser;
import br.com.alima.alurachallenge3.repositories.SystemUserRepository;
import br.com.alima.alurachallenge3.services.exceptions.DataIntegrityViolationException;
import br.com.alima.alurachallenge3.services.exceptions.ObjectNotFoundException;
import br.com.alima.alurachallenge3.services.utils.EmailSender;
import br.com.alima.alurachallenge3.services.utils.PasswordGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SystemUserService {

    public static final int PASSWORD_LENGTH = 6;
    private SystemUserRepository repository;
    private EmailSender sender;

    public List<SystemUser> findAll() {
//        List<SystemUser> users = repository.findAll();
//        users.stream().filter(u -> !u.getUsername().equals("admin@email.com.br")).collect(Collectors.toList());
        return repository.findAll().stream().filter(u -> !u.getUsername().equals("admin@email.com.br")).collect(Collectors.toList());
//        return repository.findAll();
    }
    public SystemUser findById(Integer id) {
        SystemUser user = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User Id not found!"));

        if (user.getUsername().equals("admin@email.com.br"))
            throw new DataIntegrityViolationException("Prohibited action for this user.");

        return user;
    }
    public SystemUser findByUsername(String username) {
        if (username.equals("admin@email.com.br"))
            throw new DataIntegrityViolationException("Prohibited action for this user.");

        SystemUser user = repository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("E-mail not found!"));
        return user;
    }
    public SystemUser create(SystemUser user) {

        if (user.getUsername().equals("admin@email.com.br"))
            throw new DataIntegrityViolationException("Prohibited action for this user.");

        try {
            findByUsername(user.getUsername());
            throw new DataIntegrityViolationException("E-mail already registered.");
        } catch (ObjectNotFoundException ex) {
            user.setEnabled(true);
            String password = PasswordGenerator.generateRandomPassword(PASSWORD_LENGTH);
            sender.sendEmail(user.getUsername(), "Alura Challenge Password", "Your password is '" + password + "'");
            System.out.println("Generated Passowrd: " + password);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            return repository.save(user);
        }

    }

    public SystemUser passwordReset(String username) {

        if (username.equals("admin@email.com.br"))
            throw new DataIntegrityViolationException("Prohibited action for this user.");

        SystemUser user = findByUsername(username);
        String password = PasswordGenerator.generateRandomPassword(PASSWORD_LENGTH);
        sender.sendEmail(user.getUsername(), "Alura Challenge Password", "Your password is '" + password + "'");
        System.out.println("Generated Passowrd: " + password);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return repository.save(user);

    }

    public SystemUser passwordReset(Integer id) {

        SystemUser user = findById(id);
        String password = PasswordGenerator.generateRandomPassword(PASSWORD_LENGTH);
        sender.sendEmail(user.getUsername(), "Alura Challenge Password", "Your password is '" + password + "'");
        System.out.println("Generated Passowrd: " + password);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return repository.save(user);

    }

    public void update(SystemUser newUser) {
        SystemUser oldUser = this.findById(newUser.getId());  //Retrieves data already recorded
        Authentication authentication = (new AuthenticationFacade()).getAuthentication();

        if (authentication.getName().equals(oldUser.getUsername())) //Protect a user from editing himself
            throw new DataIntegrityViolationException("A user cannot edit himself.");

        try {
            SystemUser emailUser = this.findByUsername(newUser.getUsername());  //Checks new e-mail usage
            if (oldUser.getId() != emailUser.getId())       //If used by another user but not the oldUser
                throw new DataIntegrityViolationException("E-mail already used by another user.");
            oldUser.setName(newUser.getName());             //Data update
            oldUser.setUsername(newUser.getUsername());     //Data update
            repository.save(oldUser);                       //Update
        } catch (ObjectNotFoundException ex) {              //When new email not registered
            oldUser.setName(newUser.getName());             //Data update
            oldUser.setUsername(newUser.getUsername());     //Data update
            repository.save(oldUser);                       //Update
        }
    }

    public void delete(Integer id) {
        SystemUser user = this.findById(id);
        //Protect admin user deletion
        if (user.getUsername().equals("admin@email.com.br"))
                throw new DataIntegrityViolationException("Prohibited action for this user.");
        //Protects user from deleting himself
        Authentication authentication = (new AuthenticationFacade()).getAuthentication();
        if (authentication.getName().equals(user.getUsername())) //Protect a user from editing himself
            throw new DataIntegrityViolationException("A user cannot delete himself.");
        user.setEnabled(false);
        repository.save(user);
    }
}
