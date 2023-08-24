package com.iimtiaz.rawdatatojson;

import com.iimtiaz.rawdatatojson.domain.FormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Controller
public class Home {

    FormData data;

    @PostMapping("/result")
    public String submitForm(@RequestBody FormData formData, Model model) {
        System.out.println(formData);
        data = formData;
        saveJsonToTextFile(data);
        view(formData, model);
        return "redirect:/view";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("formData", new FormData());
        return "index";
    }


    @GetMapping("/view")
    public String view(FormData formData, Model model) {
        model.addAttribute("formData", data );
        return "result";
    }

    public void saveJsonToTextFile(FormData data) {
        String json = data.toString();
        String fileName = "data.txt";
        Path filePath = Path.of(fileName);
        try {
            Files.writeString(filePath, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("JSON data saved to file: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
