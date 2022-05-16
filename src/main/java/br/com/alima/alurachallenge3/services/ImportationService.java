package br.com.alima.alurachallenge3.services;

import br.com.alima.alurachallenge3.model.Importation;
import br.com.alima.alurachallenge3.model.SystemUser;
import br.com.alima.alurachallenge3.model.Transaction;
import br.com.alima.alurachallenge3.repositories.ImportationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImportationService {
    private ImportationRepository repository;
    private SystemUserService userService;

    public void save(List<Transaction> transactions) {
        Authentication authentication = (new AuthenticationFacade()).getAuthentication();
        SystemUser user = userService.findByUsername(authentication.getName());
        Importation importation = Importation.builder()
                .id(null)
                .timeStamp(LocalDateTime.now())
                .transactionsDate(transactions.get(0).getDate())
                .user(user)
                .transactions(transactions)
                .build();
        transactions.forEach(t -> t.setImportation(importation));
        repository.save(importation);
    }

    public Optional<Importation> findByTransactionsDate(LocalDate date) {
        return repository.findImportationByTransactionsDate(date);
    }

    public Optional<Importation> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Importation> findAll() {
        return repository.findAllByOrderByTransactionsDateDesc();
    }

}
