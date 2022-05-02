package br.com.alima.alurachallenge3.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class LoginController {

//    @GetMapping
//    public String viewForm(Model model) {
//
//        return "login";
//
//    }

    @GetMapping
    public String defaultUrl() {
        return "redirect:/imports";
    }

}