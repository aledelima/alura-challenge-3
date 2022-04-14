package br.com.alima.alurachallenge3.controllers;

import br.com.alima.alurachallenge3.model.Transaction;
import br.com.alima.alurachallenge3.model.dto.ImportationDTO;
import br.com.alima.alurachallenge3.services.ImportationService;
import br.com.alima.alurachallenge3.services.TransactionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/imports")
@AllArgsConstructor
public class ImportationController {

    private TransactionService transactionService;
    private ImportationService importationService;
    private ModelMapper mapper;

    @GetMapping
    public String viewForm(Model model) {
//        List<ImportationDTO> imports = importationService.findAll()
//                .stream().map(i -> mapper.map(i, ImportationDTO.class)).collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        List<ImportationDTO> imports = importationService.findAll()
                .stream().map(i -> new ImportationDTO(i.getTransactionsDate().toString(), i.getTimeStamp().format(formatter))).collect(Collectors.toList());


//        String hojeFormatado = hoje.format(formatter);

        model.addAttribute("imports", imports);
        return "form";
    }

    @PostMapping
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize()/1000000.0 + "MB");

        //Check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "The file loaded is empty.");
            return "redirect:/imports";
        }

        //File reading
        BufferedReader br;
        try {
             br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("message", "Invalid Input format.");
            return "redirect:/imports";
        }

        //Lines from files to Object Transaction
        List<Transaction> transactions = null;
        try {
            transactions = transactionService.fileToObj(br);
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("message", "Invalid Input format.");
            return "redirect:/imports";
        }
        System.out.println();
        transactions.forEach(t -> System.out.println(t));

        //Checks if date is already uploaded
        if (importationService.findByTransactionsDate(transactions.get(0).getDate()).isPresent()) {
            attributes.addFlashAttribute("message", "Date already uploaded.");
            return "redirect:/imports";
        }

        importationService.save(transactions);

//        if (transactionService.containsDate(transactions)) {
//            attributes.addFlashAttribute("message", "Date already uploaded.");
//            return "redirect:/imports";
//        }

//        transactionService.saveAll(transactions);

        attributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/imports";

    }

}