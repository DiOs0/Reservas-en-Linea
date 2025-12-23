package com.uce.data.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;




@Data //Tiene metodos que lo hace una clase de datos
@Builder
@Entity
public class ReservaEntity {



    @Id
    private String reservaId;

    
    private String userName;
    private Date fechaReserva;
    private String estadoReserva;
    private Integer mesaReservada;
    private Integer numeroComensales;
    private String emailUser;


}
