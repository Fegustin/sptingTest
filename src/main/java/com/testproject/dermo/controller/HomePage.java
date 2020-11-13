package com.testproject.dermo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePage {

    @GetMapping("/home")
    private String home(@RequestParam(value = "name", defaultValue = "name") String name,
                        @RequestParam(value = "surname", defaultValue = "name") String surname,
                        Model model) {
        model.addAttribute("message", "Hello " + name + " " + surname);

        return "allPeople";
    }

    @GetMapping("/page")
    private String secondPage() {
        return "page";
    }
}
