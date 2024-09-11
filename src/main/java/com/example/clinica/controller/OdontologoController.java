package com.example.clinica.controller;

import com.example.clinica.entity.Odontologo;
import com.example.clinica.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;
import java.util.List;
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
        // aca jackson convierte el objeto JSON a un objeto Java "odontologo"
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

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/buscarporpartematricula/{parteMatricula}")
    public ResponseEntity<List<Odontologo>> buscarPorParteMatricula(@PathVariable String parteMatricula) {
        return ResponseEntity.ok(odontologoService.buscarPorParteMatricula(parteMatricula));
    }

    @GetMapping("/buscarporparteapellido/{parteApellido}")
    public ResponseEntity<List<Odontologo>> buscarPorApellido(@PathVariable String parteApellido) {
        return ResponseEntity.ok(odontologoService.buscarPorParteApellido(parteApellido));
    }

    @GetMapping("/buscarpornombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(odontologoService.buscarPorNombre(nombre));
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado.isPresent()) {
            odontologoService.modificarOdontologo(odontologo);
            String jsonResponse = "{\"mensaje\": \"El odontologo fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        Optional<Odontologo>  odontologoEncontrado = odontologoService.buscarPorId(id);
        if(odontologoEncontrado.isPresent()) {
            odontologoService.eliminarOdontologo(id);
            String jsonResponse = "{\"mensaje\": \"El odontologo fue eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
