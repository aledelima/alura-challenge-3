package br.com.alima.alurachallenge3.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NewUserDTO {

    @NotBlank
    private String name;
    @Email @NotBlank
    private String username;

}
