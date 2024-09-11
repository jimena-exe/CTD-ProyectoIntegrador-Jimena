package com.example.clinica.service.impl;


import com.example.clinica.entity.Paciente;
import com.example.clinica.exception.BadRequestException;
import com.example.clinica.exception.ResourceNotFoundException;
import com.example.clinica.repository.IPacienteRepository;
import com.example.clinica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    //Excepciones en guardar, buscar por id y eliminar

    public static final Logger logger = LoggerFactory.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        try {
            logger.info("Inicia el guardado del paciente");
            return pacienteRepository.save(paciente);
        }catch (RuntimeException e){
            logger.error("Error guardando el paciente " + e);
            throw new BadRequestException("El paciente no pudo ser guardado");
        }
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) {
        Optional<Paciente> pacienteEncontrado = buscarPorId(id);
        if (pacienteEncontrado.isPresent()){
            pacienteRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("El paciente no ha sido encontrado");
        }

    }

    @Override
    public List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre) {
        return pacienteRepository.findByApellidoAndNombre(apellido, nombre);
    }

    @Override
    public List<Paciente> buscarPorUnaParteApellido(String parte){
        return pacienteRepository.buscarPorParteApellido(parte);
    }

}
