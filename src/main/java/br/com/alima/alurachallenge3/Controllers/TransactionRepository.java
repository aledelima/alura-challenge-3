package br.com.alima.alurachallenge3.Controllers;

import br.com.alima.alurachallenge3.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    public List<Transaction> findAllByTimeStamp(String timeStamp);
    public List<Transaction> findAllByDate(LocalDate date);

}
