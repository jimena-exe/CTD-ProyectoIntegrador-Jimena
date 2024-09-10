package com.example.clinica.service;

import com.example.clinica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardarPaciente(Paciente paciente);

    Optional<Paciente> buscarPorId(Integer id);
    List<Paciente> buscarTodos();
    List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre);
    List<Paciente> buscarPorUnaParteApellido(String parte);

    void modificarPaciente(Paciente paciente);

    void eliminarPaciente(Integer id);


}
