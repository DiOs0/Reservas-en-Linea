package com.uce.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data //Tiene metodos que lo hace una clase de datos
@Builder
public class UsuarioEntity {

    @Id
    private Integer userId;

    private String nombre;
    private String apellido;
    private Integer edad;
    private Boolean isPresent;

}
