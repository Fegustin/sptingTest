package com.testproject.dermo.controller;

import com.testproject.dermo.model.Person;
import com.testproject.dermo.model.User;
import com.testproject.dermo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PeopleController {

    private final PersonRepository personRepository;

    @Autowired
    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    private String index() {
        return "home";
    }

    @GetMapping("/people")
    private String people(Model model) {
        model.addAttribute("person", new Person());
        return "people";
    }

    @PostMapping("people")
    private String setUser(@ModelAttribute("person") Person person) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        person.setAuthor(user);
        personRepository.save(person);
        return "redirect:/allPeople";
    }

    @GetMapping("/allPeople")
    private String users(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("persons", personRepository.findAll());
        return "allPeople";
    }

    @PostMapping("/allPeople/filter")
    private String filter(@ModelAttribute("person") Person person, Model model) {
        if (person.getName().isEmpty()) model.addAttribute("persons", personRepository.findAll());
        else model.addAttribute("persons", personRepository.findByName(person.getName()));
        return "allPeople";
    }
}
