package com.iimtiaz.rawdatatojson;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Home {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("FormData", new FormData());
        model.addAttribute("TopicData", new TopicData());
        model.addAttribute("ContentData", new ContentData());
        return "index";
    }

    @PostMapping("/submit-form")
    public String submitForm(@RequestBody FormData formData, Model model) {
        // You can process the received data here and perform any necessary actions.
        // For demonstration purposes, let's just return a success message.
        System.out.println(formData);
        model.addAttribute("data", formData);
        return "result";
    }

}
