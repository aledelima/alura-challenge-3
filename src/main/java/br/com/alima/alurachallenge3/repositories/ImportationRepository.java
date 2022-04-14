package br.com.alima.alurachallenge3.repositories;

import br.com.alima.alurachallenge3.model.Importation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImportationRepository extends JpaRepository<Importation, Integer> {

    List<Importation> findAllByOrderByTransactionsDateDesc();
    Optional<Importation> findImportationByTransactionsDate(LocalDate date);

}
