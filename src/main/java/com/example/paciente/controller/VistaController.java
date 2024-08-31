package com.example.paciente.controller;

import com.example.paciente.entity.Odontologo;
import com.example.paciente.entity.Paciente;
import com.example.paciente.service.impl.OdontologoService;
import com.example.paciente.service.impl.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VistaController {

    private PacienteService pacienteService;
    public VistaController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //localhost:8080/index?id=1&nombre=paciente1
//    @GetMapping("/index")
//    public String buscarPaciente(Model model, @RequestParam Integer id){
//        Paciente paciente = pacienteService.buscarPorId(id).get();
//
//        model.addAttribute("nombre", paciente.getNombre());
//        model.addAttribute("apellido", paciente.getApellido());
//        return "vista/paciente";
//    }

}
