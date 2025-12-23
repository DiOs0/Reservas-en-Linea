package com.uce.logic.usercases;

import com.uce.data.entities.ReservaEntity;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetConfirmationUserCase {


    public String generarConfirmacion(ReservaEntity reserva,String id) throws InterruptedException {


        Thread.sleep(2000);

        return String.format(
            "CONFIRMACIÓN GENERADA:\n" +
            "----------------------\n" +
            "ID Reserva: %s\n" +
            "Fecha:      %s\n" +
            "A nombre de: %s\n" +
            "----------------------",
            id,
            reserva.getFechaReserva().toString(),
            reserva.getUserName()
        );
    }
}
