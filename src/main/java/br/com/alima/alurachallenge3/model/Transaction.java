package br.com.alima.alurachallenge3.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

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
    private BigDecimal ammount;
    private String timeStamp;

}