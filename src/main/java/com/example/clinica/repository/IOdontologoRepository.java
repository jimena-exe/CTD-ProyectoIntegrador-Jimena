package com.example.clinica.repository;

import com.example.clinica.entity.Odontologo;
import com.example.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {

    //Encontrar por parte del nombre
    List<Odontologo> findByNombre(String nombre);

    //encontrar por parte del apellido
    @Query("Select o from Odontologo o where LOWER(o.apellido) LIKE LOWER(CONCAT('%',:parteApellido,'%'))")
    List<Odontologo> buscarPorParteApellido(String parteApellido);

    //encontrar por matricula
    @Query("Select o from Odontologo o where LOWER(o.numeroMatricula) LIKE LOWER(CONCAT('%',:parteMatricula,'%'))")
    List<Odontologo> buscarPorParteMatricula(String parteMatricula);
}
