package br.com.alima.alurachallenge3.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class NewUserDTO {

    @NotEmpty
    private String name;
    @Email
    private String username;

}
