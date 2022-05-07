package br.com.alima.alurachallenge3.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUserDTO {

    @NotNull
    private Integer id;
    @NotBlank
    private String name;
    @Email @NotBlank
    private String username;

}
