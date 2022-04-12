package br.com.alima.alurachallenge3.services;

import br.com.alima.alurachallenge3.Controllers.TransactionRepository;
import br.com.alima.alurachallenge3.model.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {

    private final String COMMA_DELIMITER = ",";
    private TransactionRepository repository;

    public boolean saveAll(BufferedReader br) {
        List<Transaction> transactions = fileToObj(br);
        if (containsDate(transactions) == true) return false;
        repository.saveAll(fileToObj(br));
        return true;
    }

    public boolean saveAll(List<Transaction> transactions) {
        repository.saveAll(transactions);
        return true;
    }

    public boolean containsDate(List<Transaction> transactions) {
        return (repository.findAllByDate(transactions.get(0).getDate()).size() > 0);
    }

    public List<Transaction> filterValidDates(List<Transaction> transactions) {
        List<Transaction> valids = new ArrayList<>();
        LocalDate date = transactions.get(0).getDate();
        return transactions.stream().filter(t -> t.getDate().equals(date)).collect(Collectors.toList());
    }

    private Transaction lineToTransaction(String line) {
        String[] values = line.split(COMMA_DELIMITER);
        String[] timeStamp = values[7].split("T");
        Transaction t = Transaction.builder()
                .id(null)
                .fromBank(values[0])
                .fromBranch(values[1])
                .fromAccount(values[2])
                .toBank(values[3])
                .toBranch(values[4])
                .toAccount(values[5])
                .ammount(new BigDecimal(values[6]))
                .date(LocalDate.parse(timeStamp[0]))
                .time(LocalTime.parse(timeStamp[1]))
                .build();
        return t;
    }

    public List<Transaction> fileToObj(BufferedReader br) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String line = br.readLine();
            Transaction t = lineToTransaction(line);
            LocalDate date = (lineToTransaction(line)).getDate();
            if (t.getDate().equals(date)) transactions.add(t);
            while ((line = br.readLine()) != null) {
                t = lineToTransaction(line);
                if (t.getDate().equals(date)) transactions.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

}
