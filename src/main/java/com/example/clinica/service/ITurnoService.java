package com.example.clinica.service;
import com.example.clinica.dto.request.TurnoModifyDto;
import com.example.clinica.dto.request.TurnoRequestDto;
import com.example.clinica.dto.response.TurnoResponseDto;
import com.example.clinica.entity.Turno;


import java.util.List;
import java.util.Optional;


public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto);

    Optional<TurnoResponseDto> buscarPorId(Integer id);
    List<TurnoResponseDto> buscarTodos();
    Optional<TurnoResponseDto> buscarTurnosPorPaciente(String pacienteApellido);

    void modificarTurnos(TurnoModifyDto turnoModifyDto);

    void eliminarTurno(Integer id);

}
