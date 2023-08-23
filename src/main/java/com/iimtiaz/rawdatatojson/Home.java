package com.iimtiaz.rawdatatojson;

import com.iimtiaz.rawdatatojson.domain.FormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Home {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("formDate", new FormData());
        return "index";
    }

    @PostMapping("/result")
    public String submitForm(@RequestBody FormData formData, Model model) {
        System.out.println(formData);
        model.addAttribute("data", formData);
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String view(Model model) {
        return "result";
    }

}
