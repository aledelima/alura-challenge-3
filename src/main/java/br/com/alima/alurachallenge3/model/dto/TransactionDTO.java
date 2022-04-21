package br.com.alima.alurachallenge3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private String fromBank;
    private String fromBranch;
    private String fromAccount;
    private String toBank;
    private String toBranch;
    private String toAccount;
    private BigDecimal amount;
    private LocalDate date;
    private LocalTime time;

}
