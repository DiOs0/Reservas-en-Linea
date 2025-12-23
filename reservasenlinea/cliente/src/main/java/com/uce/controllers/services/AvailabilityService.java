package com.uce.controllers.services;

import java.sql.Date;
import java.util.List;

import com.uce.logic.usercases.CheckAvailabilityUserCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AvailabilityService {

    private final CheckAvailabilityUserCase checkUseCase;

    @Inject
    public AvailabilityService(CheckAvailabilityUserCase checkUseCase) {
        this.checkUseCase = checkUseCase;
    }

    public void consultarDisponibilidad(Date fecha) {
        List<Integer> disponibles = checkUseCase.obtenerMesasDisponibles(fecha);

        if (disponibles.isEmpty()) {
            System.out.println("No hay mesas disponibles para la fecha: " + fecha);
        } else {
            System.out.println("Mesas libres para el " + fecha + ":");
            for (Integer mesaId : disponibles) {
                System.out.println("-> Mesa Número: " + mesaId);
            }
        }
    }
}
