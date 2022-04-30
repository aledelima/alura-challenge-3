package br.com.alima.alurachallenge3.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    @GetMapping
    public String viewForm(Model model) {

        return "login";

    }

}