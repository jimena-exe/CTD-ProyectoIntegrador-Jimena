package com.example.clinica.service.impl;

import com.example.clinica.dto.request.TurnoModifyDto;
import com.example.clinica.dto.request.TurnoRequestDto;
import com.example.clinica.dto.response.TurnoResponseDto;
import com.example.clinica.entity.Odontologo;
import com.example.clinica.entity.Paciente;
import com.example.clinica.entity.Turno;
import com.example.clinica.exception.BadRequestException;
import com.example.clinica.exception.ResourceNotFoundException;
import com.example.clinica.repository.ITurnoRepository;
import com.example.clinica.service.IOdontologoService;
import com.example.clinica.service.IPacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class TurnoServiceTest {

    static final Logger logger = LoggerFactory.getLogger(TurnoServiceTest.class);

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IOdontologoService odontologoService;

    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Paciente paciente;
    private Odontologo odontologo;
    private Turno turno;
    private TurnoResponseDto turnoResponseDto;

    @BeforeEach
    void cargarDatos() {
        // Crear y guardar el paciente
        paciente = new Paciente();
        paciente.setNombre("Juan");
        paciente.setApellido("Pérez");
        paciente.setDni("123456781");  // Proporciona un DNI válido
        paciente.setFechaIngreso(LocalDate.now());
        try {
            paciente = pacienteService.guardarPaciente(paciente);
        } catch (BadRequestException e) {
            fail("No se pudo guardar el paciente: " + e.getMessage());
        }

        // Crear y guardar el odontólogo
        odontologo = new Odontologo();
        odontologo.setApellido("Castro");
        odontologo.setNombre("Maria");
        odontologo.setNumeroMatricula("0987654321");
        try {
            odontologo = odontologoService.guardarOdontologo(odontologo);
        } catch (BadRequestException e) {
            fail("No se pudo guardar el odontólogo: " + e.getMessage());
        }

        // Crear y guardar el turno
        TurnoRequestDto turnoRequestDto = new TurnoRequestDto(
                paciente.getId(),
                odontologo.getId(),
                LocalDate.now().toString()
        );

        try {
            turnoResponseDto = turnoService.guardarTurno(turnoRequestDto);
            turno = modelMapper.map(turnoResponseDto, Turno.class);
        } catch (BadRequestException e) {
            fail("No se pudo guardar el turno: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Testear que un turno fue cargado correctamente")
    void caso1() {
        assertNotNull(turnoResponseDto.getId());
    }

    @Test
    @DisplayName("Testear que un turno pueda acceder por id")
    void caso2() {
        Integer id = turnoResponseDto.getId();
        Optional<TurnoResponseDto> turnoRecuperado = turnoService.buscarPorId(id);
        assertTrue(turnoRecuperado.isPresent());
        assertEquals(id, turnoRecuperado.get().getId());
    }

    @Test
    @DisplayName("Listar todos los turnos")
    void caso3() {
        List<TurnoResponseDto> turnos = turnoService.buscarTodos();
        assertFalse(turnos.isEmpty());
    }

    @Test
    @DisplayName("Modificar un turno correctamente")
    void modificarTurnoTest() {
        TurnoModifyDto turnoModifyDto = new TurnoModifyDto();
        turnoModifyDto.setId(turno.getId());
        turnoModifyDto.setPaciente_id(paciente.getId());
        turnoModifyDto.setOdontologo_id(odontologo.getId());
        turnoModifyDto.setFecha(LocalDate.now().plusDays(1).toString());

        turnoService.modificarTurnos(turnoModifyDto);

        Optional<TurnoResponseDto> turnoModificado = turnoService.buscarPorId(turno.getId());
        assertTrue(turnoModificado.isPresent());
        assertEquals(LocalDate.now().plusDays(1).toString(), turnoModificado.get().getFecha());
    }

    @Test
    @DisplayName("Eliminar un turno correctamente")
    void eliminarTurnoTest() {
        Integer id = turnoResponseDto.getId();

        turnoService.eliminarTurno(id);

        assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarPorId(id));
    }

    @Test
    @DisplayName("Buscar turno por apellido del paciente")
    void buscarTurnosPorPacienteTest() {
        Optional<TurnoResponseDto> turno = turnoService.buscarTurnosPorPaciente("Pérez");
        assertTrue(turno.isPresent());
        assertEquals("Pérez", turno.get().getPacienteResponseDto().getApellido());
    }

    @Test
    @DisplayName("Lanzar BadRequestException cuando el paciente u odontologo no existen")
    void pacienteOdontologoNoExistenteTest() {
        TurnoRequestDto turnoRequestDto = new TurnoRequestDto(
                999, // ID inexistente
                odontologo.getId(),
                LocalDate.now().toString()
        );
        assertThrows(BadRequestException.class, () -> turnoService.guardarTurno(turnoRequestDto));
    }

    @Test
    @DisplayName("Lanzar ResourceNotFoundException cuando el turno no existe")
    void buscarTurnoNoExistenteTest() {
        Integer idInexistente = 999;
        assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarPorId(idInexistente));
    }
}
