package com.example.paciente.service;

import com.example.paciente.entity.Odontologo;
import com.example.paciente.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);

    List<Odontologo> buscarTodos();

    void modificarOdontologo(Odontologo paciente);

    void eliminarOdontologo(Integer id);
    Optional<Odontologo> buscarPorId(Integer id);
}
