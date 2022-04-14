package br.com.alima.alurachallenge3.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImportationDTO {

    private String transactionsDate;
    private String timeStamp;

}
