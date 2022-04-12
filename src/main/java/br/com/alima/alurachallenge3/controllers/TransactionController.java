package br.com.alima.alurachallenge3.controllers;

import br.com.alima.alurachallenge3.model.Transaction;
import br.com.alima.alurachallenge3.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Controller
@RequestMapping("/files")
@AllArgsConstructor
public class TransactionController {

    private TransactionService service;

    @GetMapping
    public String viewForm() {
        return "form";
    }

    @PostMapping
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize()/1000000.0 + "MB");

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Empty file.");
            return "redirect:/files";
        }

        BufferedReader br;
        try {
             br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("message", "Invalid Input format.");
            return "redirect:/files";
        }

        List<Transaction> transactions = service.fileToObj(br);
        System.out.println();
        transactions.forEach(t -> System.out.println(t));

        if (service.containsDate(transactions)) {
            attributes.addFlashAttribute("message", "Transactions already loaded for this date.");
            return "redirect:/files";
        }

        transactions = service.filterValidDates(transactions);
        service.saveAll(transactions);

        attributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/files";

    }

}