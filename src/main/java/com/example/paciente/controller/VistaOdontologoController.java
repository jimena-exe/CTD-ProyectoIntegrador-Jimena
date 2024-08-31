package com.example.paciente.controller;

import com.example.paciente.entity.Odontologo;
import com.example.paciente.service.impl.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VistaOdontologoController {

    private OdontologoService odontologoService;

    public VistaOdontologoController (OdontologoService odontologoServive) {
        this.odontologoService = odontologoServive;
    }

    @GetMapping("/index")
    public String bienvenidaOdontologo(Model model, @RequestParam Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id).get();
        model.addAttribute("nombre",odontologo.getNombre());
        model.addAttribute("apellido",odontologo.getApellido());
        return "vista/odontologo";
    }

}
