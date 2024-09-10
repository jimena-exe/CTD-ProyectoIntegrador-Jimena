package com.example.clinica.repository;

import com.example.clinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {

    //Encontrar por parte del nombre
    List<Odontologo> findByNombre(String nombre);

    //encontrar por parte del apellido

    //encontrar por matricula?

}
