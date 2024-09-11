package com.example.clinica.entity;
import com.example.clinica.utils.GsonProvider;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(min = 10, max = 10)
    private String numeroMatricula;
    @Size(min = 3, max = 30)
    private String apellido;
    @Size(min = 3, max = 50)
    private String nombre;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "odontologo-turno")

    private Set<Turno> turnoSet;

    @Override
    public String toString() {
        return GsonProvider.getGson().toJson(this);
    }
}
