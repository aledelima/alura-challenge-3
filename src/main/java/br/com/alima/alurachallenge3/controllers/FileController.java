package br.com.alima.alurachallenge3.controllers;

import br.com.alima.alurachallenge3.model.Transaction;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileController {

//    private final String UPLOAD_DIR = "./uploads/";
    private final String COMMA_DELIMITER = ",";

    @GetMapping
    public String viewForm() {
        return "form";
    }

    @PostMapping
//    @PostMapping("/uploadCSV")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize()/1000000.0 + "MB");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
            String line;
            List<Transaction> transactions = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Transaction t = Transaction.builder()
                        .id(null)
                        .fromBank(values[0])
                        .fromBranch(values[1])
                        .fromAccount(values[2])
                        .toBank(values[3])
                        .toBranch(values[4])
                        .toAccount(values[5])
                        .ammount(new BigDecimal(values[6]))
                        .timeStamp(values[7])
                        .build();

                transactions.add(t);
            }
            System.out.println();
            transactions.forEach(x -> System.out.println(x));
        } catch (IOException e) {
            e.printStackTrace();
        }

        attributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/files";

    }

}