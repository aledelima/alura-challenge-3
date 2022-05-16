package br.com.alima.alurachallenge3.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    private SystemUser user;

    @ManyToOne
    @Nullable
    private Importation importation;
}