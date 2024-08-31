package com.example.paciente.service.impl;

import com.example.paciente.entity.Odontologo;
import com.example.paciente.repository.IOdontologoRepository;
import com.example.paciente.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    @Autowired
    private IOdontologoRepository odontologoRepository;


    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {

        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {

        return odontologoRepository.findById(id);
    }
}
