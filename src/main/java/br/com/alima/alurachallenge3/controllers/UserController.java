package br.com.alima.alurachallenge3.controllers;

import br.com.alima.alurachallenge3.model.SystemUser;
import br.com.alima.alurachallenge3.model.dto.NewUserDTO;
import br.com.alima.alurachallenge3.model.dto.UpdateUserDTO;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private SystemUserService userService;
    private ModelMapper mapper;

    @GetMapping
    public String findAll(Model model) {

        List<SystemUser> users = userService.findAll();
        users = users.stream().filter(u -> !u.getUsername().equals("admin@email.com.br")).collect(Collectors.toList()); //Remove user admin
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, RedirectAttributes attributes, Model model, UpdateUserDTO dto) {

        try {
            SystemUser user = userService.findById(id);
            if (user.getUsername().equals("admin@email.com.br"))
                throw new Exception("Prohibited action for this user.");
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
            if (userService.findById(id).getUsername().equals("admin@email.com.br"))
                throw new Exception("Prohibited action for this user.");
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
            if (userService.findById(user.getId()).getUsername().equals("admin@email.com.br"))
                throw new Exception("Prohibited user!");
            userService.update(user);
        } catch (Exception ex) {
            attributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/users/"+dto.getId();
        }
//        } catch (DataIntegrityViolationException ex) {
////            attributes.addFlashAttribute("message", "E-mail already registered.");
//            attributes.addFlashAttribute("message", ex.getMessage());
//            return "redirect:/users/"+dto.getId();
//        } catch (ObjectNotFoundException ex) {
////            attributes.addFlashAttribute("message", "E-mail already registered.");
//            attributes.addFlashAttribute("message", ex.getMessage());
//            return "redirect:/users/"+dto.getId();
//        }

        attributes.addFlashAttribute("message", "User edited successfully.");
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, NewUserDTO dto, RedirectAttributes attributes) {
        try {
            if (userService.findById(id).getUsername().equals("admin@email.com.br"))
                throw new Exception("Prohibited user!");
            userService.delete(id);
        } catch (Exception ex) {
            attributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/users";
        }
//        } catch (DataIntegrityViolationException ex) {
//            attributes.addFlashAttribute("message", ex.getMessage());
//            return "redirect:/users";
//        } catch (ObjectNotFoundException ex) {
//            attributes.addFlashAttribute("message", ex.getMessage());
//            return "redirect:/users";
//        }
        attributes.addFlashAttribute("message", "User deleted successfully.");
        return "redirect:/users";
    }

}