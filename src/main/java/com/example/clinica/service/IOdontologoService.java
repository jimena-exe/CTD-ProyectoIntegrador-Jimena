package com.example.clinica.service;

import com.example.clinica.entity.Odontologo;
import com.example.clinica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);

    Optional<Odontologo> buscarPorId(Integer id);
    List<Odontologo> buscarTodos();

    void modificarOdontologo(Odontologo paciente);

    void eliminarOdontologo(Integer id);

}
