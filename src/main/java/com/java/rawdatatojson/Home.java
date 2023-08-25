package com.java.rawdatatojson;

import com.java.rawdatatojson.domain.FormData;
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

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("formData", new FormData());
        return "index";
    }

    @PostMapping("/result")
    public String submitForm(@RequestBody FormData formData) {
        System.out.println(formData);
        saveJsonToTextFile(formData);
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String view(Model model) {
        String jsonFromFile = readJsonFromTextFile();
        model.addAttribute("formData", jsonFromFile);
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

    public String readJsonFromTextFile() {
        String fileName = "data.txt";
        Path filePath = Path.of(fileName);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading JSON data from file.";
        }
    }
}
