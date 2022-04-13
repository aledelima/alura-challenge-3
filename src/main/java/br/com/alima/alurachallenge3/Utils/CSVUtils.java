package br.com.alima.alurachallenge3.Utils;

import br.com.alima.alurachallenge3.model.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    private static final String COMMA_DELIMITER = ",";
    private static final String TIMESTAMP_DELIMITER = "T";

    public static List<Transaction> fileToObj(BufferedReader br) throws IOException {
        List<Transaction> transactions = new ArrayList<>();

        //Gets date from the first VALID line
        String line;
        LocalDate date = null;
        while ((line = br.readLine()) != null) {
            String[] record = lineToRecord(line);
            if (isRecordValid(record)) {
                Transaction t = recordToTransaction(record);
                date = t.getDate();
                transactions.add(t);
                break;
            }
        }

        //Valid records with same date
        while ((line = br.readLine()) != null) {
            String[] record = lineToRecord(line);
            if (isRecordValid(record)) {
                Transaction t = recordToTransaction(record);
                if (t.getDate().equals(date)) transactions.add(t);
            }
        }
        return transactions;
    }

    public static String[] lineToRecord(String line) {
        String[] record = {"", "", "", "", "", "", "", "", ""};
        String[] values = line.split(COMMA_DELIMITER);
        for (int i=0; i<values.length; i++) record[i] = values[i];

        if (values.length==8) {
            if (values[7].matches("[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}T[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}")) {
                String[] timeStamp = values[7].split(TIMESTAMP_DELIMITER);
                for (int i=0; i<(timeStamp.length); i++) record[7+i] = timeStamp[i];
            }
        }

        for (String s: record) System.out.println(s);
        System.out.println();

        return record;
    }

    public static Transaction recordToTransaction(String[] record) {
        Transaction t = null;
        if (isRecordValid(record)) {
            t = Transaction.builder()
                    .id(null)
                    .fromBank(record[0])
                    .fromBranch(record[1])
                    .fromAccount(record[2])
                    .toBank(record[3])
                    .toBranch(record[4])
                    .toAccount(record[5])
                    .amount(new BigDecimal(record[6]))
                    .date(LocalDate.parse(record[7]))
                    .time(LocalTime.parse(record[8]))
                    .build();
        }
        return t;
    }

    public static boolean isRecordValid(String[] record) {
        if (record.length != 9) return false;                    //Eight fields
        for (String s: record)
            if (s.trim().isEmpty()) return false;               //Fields empty
        return true;
    }

}