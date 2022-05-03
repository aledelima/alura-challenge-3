package br.com.alima.alurachallenge3.controllers;

import br.com.alima.alurachallenge3.model.SystemUser;
import br.com.alima.alurachallenge3.model.dto.NewUserDTO;
import br.com.alima.alurachallenge3.services.SystemUserService;
import br.com.alima.alurachallenge3.services.exceptions.DataIntegrityViolationException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private SystemUserService userService;
    private ModelMapper mapper;

    @GetMapping
    public String findAll(Model model) {

        List<SystemUser> users = userService.findAll();

        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{email}")
    public String findByEmail(@PathVariable String email, RedirectAttributes attributes, Model model) {

        try {
            SystemUser user = userService.findByUsername(email);
            model.addAttribute("user", user);
        } catch (Exception ex) {
            attributes.addFlashAttribute("message", "E-mail not registered.");
        }

        return "user-edition";
    }

    @GetMapping("/new")
    public String creation(NewUserDTO dto) {
        return "user-creation";
    }

    @PostMapping(value = "/create")
    public String create(@Valid NewUserDTO dto, BindingResult bind, RedirectAttributes attributes){//}, Model model) {

        if (bind.hasErrors()) return "user-creation";

        SystemUser newUser = mapper.map(dto, SystemUser.class);

        try {
            userService.create(newUser);
        } catch (DataIntegrityViolationException ex) {
            attributes.addFlashAttribute("message", "E-mail already registered.");
            return "redirect:/users/new";
        }

        attributes.addFlashAttribute("message", "User registered successfully.");
        return "redirect:/users";
    }

}