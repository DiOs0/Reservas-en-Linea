package com.uce.logic.validators;

import java.sql.Date;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CapacityValidator {

    @Inject
    private EntityManager em;

    
    private static final Long MAX_CAPACIDAD = 20L;

    public boolean hayEspacioDisponible(Date fecha) {
        // Contamos cuántas reservas activas existen para esa fecha
        Long reservasOcupadas = em.createQuery(
            "SELECT COUNT(r) FROM ReservaEntity r " +
            "WHERE r.fechaReserva = :fecha AND r.estadoReserva != 'Cancelada'", Long.class)
            .setParameter("fecha", fecha)
            .getSingleResult();

        return reservasOcupadas < MAX_CAPACIDAD;
    }
}
