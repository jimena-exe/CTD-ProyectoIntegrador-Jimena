package com.example.clinica.service.impl;


import com.example.clinica.entity.Domicilio;
import com.example.clinica.entity.Odontologo;
import com.example.clinica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class OdontologoServiceTest {
    static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    @Autowired
    OdontologoService odontologoService;
    Odontologo odontologo;
    Odontologo odontologoDesdeDb;

    @BeforeEach
    void cargarDatos() {
        Domicilio domicilio = new Domicilio(null, "Falsa", 145, "CABA", "Buenos Aires");
        odontologo = new Odontologo();
        odontologo.setApellido("Castro");
        odontologo.setNombre("Maria");
        odontologo.setNumeroMatricula("0987654321");
        odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);
    }

    @Test
    @DisplayName("Testear que un odontologo fue cargado correctamente con su domicilio")
    void caso1() {
        assertNotNull(odontologoDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que un odontologo pueda acceder por id")
    void caso2() {
        //Dado
        Integer id = odontologoDesdeDb.getId();
        //cuando
        Odontologo odontologoRecuperado = odontologoService.buscarPorId(id).get();
        // entonces
        assertEquals(id, odontologoRecuperado.getId());
    }

    @Test
    @DisplayName("Listar todos los odontologos")
    void caso3() {
        List<Odontologo> odontologos;
        odontologos = odontologoService.buscarTodos();
        assertFalse(odontologos.isEmpty());
    }

    @Test
    @DisplayName("Modificar un odontologo correctamente")
    void modificarOdontologoTest() {
        odontologoDesdeDb.setNombre("NuevoNombre");
        odontologoService.modificarOdontologo(odontologoDesdeDb);

        Odontologo odontologoModificado = odontologoService.buscarPorId(odontologoDesdeDb.getId()).get();
        assertEquals("NuevoNombre", odontologoModificado.getNombre());
    }

    @Test
    @DisplayName("Eliminar un odontologo correctamente")
    void eliminarOdontologoTest() {
        Integer id = odontologoDesdeDb.getId();

        odontologoService.eliminarOdontologo(id);

        assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarPorId(id));
    }

    @Test
    @DisplayName("Buscar odontologo por parte del apellido")
    void buscarPorApellidoTest() {
        List<Odontologo> odontologos = odontologoService.buscarPorParteApellido("Castro");
        assertFalse(odontologos.isEmpty());
        assertEquals("Castro", odontologos.get(0).getApellido());
    }

    @Test
    @DisplayName("Lanzar ResourceNotFoundException cuando el odontologo no existe")
    void buscarOdontologoNoExistenteTest() {
        Integer idInexistente = 999;
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarPorId(idInexistente));
    }

}