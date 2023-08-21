package com.iimtiaz.rawdatatojson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/result")
    public String result(){
        return "result";
    }

}
