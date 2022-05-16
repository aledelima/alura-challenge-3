package br.com.alima.alurachallenge3.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Importation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime timeStamp;
    @Column(unique = true)
    private LocalDate transactionsDate;
    @ManyToOne
    private SystemUser user;
    @OneToMany(mappedBy = "importation", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

}