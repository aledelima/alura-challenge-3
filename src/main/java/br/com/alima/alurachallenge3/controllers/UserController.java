package br.com.alima.alurachallenge3.controllers;

import br.com.alima.alurachallenge3.model.SystemUser;
import br.com.alima.alurachallenge3.model.dto.NewUserDTO;
import br.com.alima.alurachallenge3.model.dto.UpdateUserDTO;
import br.com.alima.alurachallenge3.services.SystemUserService;
import br.com.alima.alurachallenge3.services.exceptions.DataIntegrityViolationException;
import br.com.alima.alurachallenge3.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, RedirectAttributes attributes, Model model, UpdateUserDTO dto) {

        try {
            SystemUser user = userService.findById(id);
            mapper.map(user, dto);
            model.addAttribute("updateUserDTO", dto);
        } catch (Exception ex) {
            attributes.addFlashAttribute("message", "User Id not registered.");
            return "users";
        }

        return "user-edition";
    }

    @GetMapping("/{id}/reset")
    public String resetPassword(@PathVariable Integer id, RedirectAttributes attributes, Model model) {

        try {
            userService.passwordReset(id);
        } catch (Exception ex) {
            attributes.addFlashAttribute("message", "User not registered.");
        }
        attributes.addFlashAttribute("message", "Password reset successfully.");
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String creation(NewUserDTO dto) {
        return "user-creation";
    }

    @PostMapping
    public String create(@Valid NewUserDTO dto, BindingResult bind, RedirectAttributes attributes){

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
    @PostMapping(value = "/{id}/update")
    public String update(@Valid UpdateUserDTO dto, BindingResult bind, RedirectAttributes attributes){

        if (bind.hasErrors()) return "user-edition";

        SystemUser user = mapper.map(dto, SystemUser.class);

        try {
            userService.update(user);
        } catch (DataIntegrityViolationException ex) {
            attributes.addFlashAttribute("message", "E-mail already registered.");
            return "redirect:/users/"+dto.getId();
        }

        attributes.addFlashAttribute("message", "User edited successfully.");
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, NewUserDTO dto, RedirectAttributes attributes) {
        try {
            userService.delete(id);
        } catch (ObjectNotFoundException ex) {
            attributes.addFlashAttribute("message", "User Id not found.");
            return "redirect:/users";
        }
        attributes.addFlashAttribute("message", "User deleted successfully.");
        return "redirect:/users";
    }

}