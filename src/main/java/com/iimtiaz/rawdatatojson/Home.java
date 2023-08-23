package com.iimtiaz.rawdatatojson;

import com.iimtiaz.rawdatatojson.domain.FormData;
import jdk.jfr.ContentType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Home {

    FormData formData;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("formData", new FormData());
        return "index";
    }

    @PostMapping("/result")
    public String submitForm(@RequestBody FormData formData, Model model) {
        System.out.println(formData);
        formData = formData;
        view(formData, model);
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String view(FormData formData, Model model) {
        model.addAttribute("formData", formData );
        return "result";
    }

}
