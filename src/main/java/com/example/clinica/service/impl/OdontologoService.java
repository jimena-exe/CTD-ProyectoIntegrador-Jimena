package com.example.clinica.service.impl;

import com.example.clinica.dto.response.TurnoResponseDto;
import com.example.clinica.entity.Odontologo;
import com.example.clinica.entity.Turno;
import com.example.clinica.exception.BadRequestException;
import com.example.clinica.exception.ResourceNotFoundException;
import com.example.clinica.repository.IOdontologoRepository;
import com.example.clinica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    //Excepciones en guardar, buscar por id, buscar por nombre y eliminar

    public static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);
    @Autowired
    private IOdontologoRepository odontologoRepository;


    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        try {
            logger.info("Inicia el guardado del odontologo");
            return odontologoRepository.save(odontologo);
        }catch(RuntimeException exception){
            logger.error("Ha ocurrido una excepcion al guardar el odontologo "+exception);
            throw new BadRequestException("El odontologo no pudo ser guardado "+ exception);
        }
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        Optional<Odontologo> odontologo = null;
        if (odontologo.isPresent()){
            logger.info("Se inicia la busqueda del odontologo");
            return odontologoRepository.findById(id);
        }else{
            logger.error("Ha ocurrido una excepcion al buscar el odontologo ");
            throw  new ResourceNotFoundException("El odontologo no ha sido encontrado");
        }
    }

    @Override
    public List<Odontologo> buscarPorParteApellido(String apellido) {
        return odontologoRepository.buscarPorParteApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        Optional<Odontologo> odontologoEncontrado = null;
        if (odontologoEncontrado.isPresent()){
            return odontologoRepository.findByNombre(nombre);
        }else {
            throw new ResourceNotFoundException("El odontologo no ha sido encontrado");
        }
    }

    @Override
    public List<Odontologo> buscarPorParteMatricula(String matricula) {
        return odontologoRepository.buscarPorParteMatricula(matricula);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {

        try {
            logger.info("Se inicia la modificacion del odontologo");
            odontologoRepository.save(odontologo);
        }catch(Exception exception){
            logger.error("Ha ocurrido una excepcion al modificar el odontologo "+exception);

        }


    }

    @Override
    public void eliminarOdontologo(Integer id) {
        try{
            odontologoRepository.deleteById(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Odontologo no encontrado en la base de datos");
        }
    }

}
