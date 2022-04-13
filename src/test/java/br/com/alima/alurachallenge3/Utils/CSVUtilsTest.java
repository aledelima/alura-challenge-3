package br.com.alima.alurachallenge3.Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CSVUtilsTest {

    public static final String RECORD_COMPLETE = "CAIXA ECONOMICA FEDERAL,0001,00001-1,BANCO BRADESCO,0001,00001-1,900,2022-01-01T22:30:00";
    public static final String RECORD_NO_FROM_BANK = ",0001,00001-1,BANCO BRADESCO,0001,00001-1,8000,2022-01-01T07:30:00";
    public static final String RECORD_NO_FROM_BRANCH = "BANCO SANTANDER,,00001-1,BANCO BRADESCO,0001,00001-1,210,2022-01-01T08:12:00";
    public static final String RECORD_NO_FROM_ACCOUNT = "BANCO SANTANDER,0001,,BANCO BRADESCO,0001,00001-1,79800.22,2022-01-01T08:44:00";
    public static final String RECORD_NO_TO_BANK = "BANCO BRADESCO,0001,00001-1,,0001,00002-1,11.50,2022-01-01T12:32:00";
    public static final String RECORD_NO_TO_BRANCH = "BANCO BANRISUL,0001,00001-1,BANCO BRADESCO,,00001-1,100,2022-01-01T16:30:00";
    public static final String RECORD_NO_TO_ACCOUNT = "BANCO ITAU,0001,00001-1,BANCO BRADESCO,0001,,19000.50,2022-01-01T16:55:00";
    public static final String RECORD_NO_VALUE = "BANCO INTER,0001,00001-1,BANCO BRADESCO,0001,00001-1,,2022-01-01T20:30:00";
    public static final String RECORD_NO_TIMESTAMP = "CAIXA ECONOMICA FEDERAL,0001,00001-1,BANCO BRADESCO,0001,00001-1,900,";
    public static final String RECORD_EMPTY = ",,,,,,";

    @Test
    void lineToRecordsValidLine() {
        String line = RECORD_COMPLETE;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsEmpty() {
        String line = RECORD_EMPTY;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoFromBank() {
        String line = RECORD_NO_FROM_BANK;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoFromBrach() {
        String line = RECORD_NO_FROM_BRANCH;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoFromAccount() {
        String line = RECORD_NO_FROM_ACCOUNT;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoToBank() {
        String line = RECORD_NO_TO_BANK;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoToBranch() {
        String line = RECORD_NO_TO_BRANCH;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoToAccount() {
        String line = RECORD_NO_TO_ACCOUNT;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoValue() {
        String line = RECORD_NO_VALUE;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void lineToRecordsNoTimestamp() {
        String line = RECORD_NO_TIMESTAMP;
        String[] records = CSVUtils.lineToRecord(line);
        Assertions.assertEquals(9, records.length);
    }

    @Test
    void recordValidationValidLine() {
        String line = RECORD_COMPLETE;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertTrue(CSVUtils.isRecordValid(record));
    }

    @Test
    void recordValidationEmpty() {
        String line = RECORD_EMPTY;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertFalse(CSVUtils.isRecordValid(record));
    }

    @Test
    void recordValidationNoFromBank() {
        String line = RECORD_NO_FROM_BANK;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertFalse(CSVUtils.isRecordValid(record));
    }

    @Test
    void recordValidationNoFromBrach() {
        String line = RECORD_NO_FROM_BRANCH;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertFalse(CSVUtils.isRecordValid(record));
    }

    @Test
    void recordValidationNoFromAccount() {
        String line = RECORD_NO_FROM_ACCOUNT;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertFalse(CSVUtils.isRecordValid(record));
    }

    @Test
    void recordValidationNoToBank() {
        String line = RECORD_NO_TO_BANK;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertFalse(CSVUtils.isRecordValid(record));
    }

    @Test
    void recordValidationNoToBranch() {
        String line = RECORD_NO_TO_BRANCH;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertFalse(CSVUtils.isRecordValid(record));
    }

    @Test
    void recordValidationNoToAccount() {
        String line = RECORD_NO_TO_ACCOUNT;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertFalse(CSVUtils.isRecordValid(record));
    }

    @Test
    void recordValidationNoValue() {
        String line = RECORD_NO_VALUE;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertFalse(CSVUtils.isRecordValid(record));
    }

    @Test
    void recordValidationNoTimestamp() {
        String line = RECORD_NO_TIMESTAMP;
        String[] record = CSVUtils.lineToRecord(line);
        Assertions.assertFalse(CSVUtils.isRecordValid(record));
    }

}