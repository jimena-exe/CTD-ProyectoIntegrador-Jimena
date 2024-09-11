package com.example.clinica.service;

import com.example.clinica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);

    Optional<Odontologo> buscarPorId(Integer id);

    List<Odontologo> buscarTodos();

    public List<Odontologo> buscarPorParteApellido(String apellido);

    public List<Odontologo> buscarPorNombre(String nombre);

    public List<Odontologo> buscarPorParteMatricula(String matricula);

    void modificarOdontologo(Odontologo paciente);

    void eliminarOdontologo(Integer id);

}
