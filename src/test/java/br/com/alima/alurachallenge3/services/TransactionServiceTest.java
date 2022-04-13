package br.com.alima.alurachallenge3.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceTest {

//    ,0001,00001-1,BANCO BRADESCO,0001,00001-1,8000,2022-01-01T07:30:00
//    BANCO SANTANDER,,00001-1,BANCO BRADESCO,0001,00001-1,210,2022-01-01T08:12:00
//    BANCO SANTANDER,0001,,BANCO BRADESCO,0001,00001-1,79800.22,2022-01-01T08:44:00
//    BANCO BRADESCO,0001,00001-1,,0001,00002-1,11.50,2022-01-01T12:32:00
//    BANCO BANRISUL,0001,00001-1,BANCO BRADESCO,,00001-1,100,2022-01-01T16:30:00
//    BANCO ITAU,0001,00001-1,BANCO BRADESCO,0001,,19000.50,2022-01-01T16:55:00
//    BANCO ITAU,0001,00002-1,BANCO BRADESCO,0001,00001-1,,2022-01-01T19:30:00
//    NUBANK,0001,00001-1,BANCO BRADESCO,0001,00001-1,2000,
//    BANCO INTER,0001,00001-1,BANCO BRADESCO,0001,00001-1,300,2022-01-01T20:30:00
//    CAIXA ECONOMICA FEDERAL,0001,00001-1,BANCO BRADESCO,0001,00001-1,900,2022-01-01T22:30:00

    @Autowired
    private TransactionService service;

    @Test
    void lineToRecordsValidLine() {
        String line = "CAIXA ECONOMICA FEDERAL,0001,00001-1,BANCO BRADESCO,0001,00001-1,900,2022-01-01T22:30:00";
        String[] records = service.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoFromBank() {
        String line = ",0001,00001-1,BANCO BRADESCO,0001,00001-1,8000,2022-01-01T07:30:00";
        String[] records = service.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoFromBrach() {
        String line = "BANCO SANTANDER,,00001-1,BANCO BRADESCO,0001,00001-1,210,2022-01-01T08:12:00";
        String[] records = service.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoFromAccount() {
        String line = "BANCO SANTANDER,0001,,BANCO BRADESCO,0001,00001-1,79800.22,2022-01-01T08:44:00";
        String[] records = service.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoToBank() {
        String line = "BANCO BRADESCO,0001,00001-1,,0001,00002-1,11.50,2022-01-01T12:32:00";
        String[] records = service.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoToBranch() {
        String line = "BANCO BANRISUL,0001,00001-1,BANCO BRADESCO,,00001-1,100,2022-01-01T16:30:00";
        String[] records = service.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoToAccount() {
        String line = "BANCO ITAU,0001,00001-1,BANCO BRADESCO,0001,,19000.50,2022-01-01T16:55:00";
        String[] records = service.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoValue() {
        String line = "BANCO INTER,0001,00001-1,BANCO BRADESCO,0001,00001-1,300,2022-01-01T20:30:00";
        String[] records = service.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }
//
//    @Test
//    void lineToRecordsNoTimeStamp() {
//        String line = "BANCO INTER,0001,00001-1,BANCO BRADESCO,0001,00001-1,300,";
//        String[] records = service.lineToRecords(line);
//        Assertions.assertEquals(9, records.length);
//    }


}