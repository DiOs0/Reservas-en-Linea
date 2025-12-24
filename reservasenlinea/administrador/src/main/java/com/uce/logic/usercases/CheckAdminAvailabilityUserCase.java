package com.uce.logic.usercases;

import java.sql.Date;
import java.util.List;

import com.uce.data.entities.ReservaEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CheckAdminAvailabilityUserCase {

    @Inject
    private EntityManager em;

    public List<ReservaEntity> obtenerOcupacionPorFecha(Date fecha) {
        return em.createQuery(
            "SELECT r FROM ReservaEntity r WHERE r.fechaReserva = :fecha AND r.estadoReserva != 'Cancelada'", 
            ReservaEntity.class)
            .setParameter("fecha", fecha)
            .getResultList();
    }
}
