package com.example.paciente.controller;

import com.example.paciente.entity.Odontologo;
import com.example.paciente.service.IOdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //GUARDAR
    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        // aca jackson convierte el objeto JSON a un objeto Java "paciente"
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    //BUSCAR
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //LISTAR TODOS

    //ELIMINAR

    //MODIFICAR
}
