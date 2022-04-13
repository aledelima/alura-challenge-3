package br.com.alima.alurachallenge3.services;

import br.com.alima.alurachallenge3.Utils.CSVUtils;
import br.com.alima.alurachallenge3.model.Transaction;
import br.com.alima.alurachallenge3.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {

    private final String COMMA_DELIMITER = ",";
    private TransactionRepository repository;

    public void saveAll(List<Transaction> transactions) {
        repository.saveAll(transactions);
    }

    public boolean containsDate(List<Transaction> transactions) {
        return (repository.findAllByDate(transactions.get(0).getDate()).size() > 0);
    }

    public List<Transaction> filterValidDates(List<Transaction> transactions) {
        LocalDate date = transactions.get(0).getDate();
        return transactions.stream().filter(t -> t.getDate().equals(date)).collect(Collectors.toList());
    }

    public List<Transaction> fileToObj(BufferedReader br) throws IOException {
        return CSVUtils.fileToObj(br);
    }

    public String[] lineToRecord(String line) {
        return CSVUtils.lineToRecord(line);
    }

    public boolean isRecordValid(String[] record) {
        return CSVUtils.isRecordValid(record);
    }

}
