package br.com.alima.alurachallenge3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/files")
public class FileController {

    private final String UPLOAD_DIR = "./uploads/";

    @GetMapping
    public String viewForm() {
        return "form";
    }

    @PostMapping
//    @PostMapping("/uploadCSV")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
//    public String uploadCSVFile(@RequestParam("file") MultipartFile file) {
        attributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");


        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize()/1000000.0 + "MB");

        // check if file is empty
//        if (file.isEmpty()) {
//            attributes.addFlashAttribute("message", "Please select a file to upload.");
//            return "redirect:/";
//        }

        // normalize the file path
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
//        try {
//            Path path = Paths.get(UPLOAD_DIR + fileName);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // return success response
//        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/files";

//        return ResponseEntity.ok().build();
    }

}
