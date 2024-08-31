package com.example.paciente.service;
import com.example.paciente.dto.request.TurnoModifyDto;
import com.example.paciente.dto.request.TurnoRequestDto;
import com.example.paciente.dto.response.TurnoResponseDto;
import com.example.paciente.entity.Turno;


import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto);

    Optional<Turno> buscarPorId(Integer id);

    List<TurnoResponseDto> buscarTodos();

    void modificarTurnos(TurnoModifyDto turnoModifyDto);

    void eliminarTurno(Integer id);

    Optional<Turno> buscarTurnosPorPaciente(String pacienteApellido);
}
