package com.example.paciente.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PacienteController {

    @GetMapping("/index")
    public String bienvenida(Model model){
        model.addAttribute("saludo","Bienvenido");
        return "index";
    }
}
