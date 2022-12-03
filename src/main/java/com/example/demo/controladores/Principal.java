package com.example.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Principal {
    @GetMapping("/hola-mundo")
    @ResponseBody
    public String helloWorld(Model model){
        model.addAttribute("message", "Hello World!");
        return "Bienvenido a mi Universo";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("saludo", "¡Hola murciano!");
        return "index.html";
    }
}
