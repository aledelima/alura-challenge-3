package br.com.alima.alurachallenge3.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportationDTO {

    private Integer id;
    private String transactionsDate;
    private String timeStamp;
    private String username;
    private List<TransactionDTO> transactions;

}
