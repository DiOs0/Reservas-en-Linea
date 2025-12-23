package com.uce.controllers.services;

import com.uce.data.entities.ReservaEntity;
import com.uce.logic.usercases.GetConfirmationUserCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationService {

    @Inject
    private GetConfirmationUserCase userCase;

    public String procesarNotificacion(ReservaEntity reserva,String id) throws InterruptedException {
        System.out.println("... Generando confirmación de reserva ...");
        return userCase.generarConfirmacion(reserva,id);
    }
}
