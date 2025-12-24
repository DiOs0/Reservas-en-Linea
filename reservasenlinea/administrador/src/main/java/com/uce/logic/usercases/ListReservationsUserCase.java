package com.uce.logic.usercases;

import java.util.List;

import com.uce.data.entities.ReservaEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ListReservationsUserCase {

    @Inject
    private EntityManager em;

    public List<ReservaEntity> obtenerTodasLasReservas() {
        return em.createQuery("SELECT r FROM ReservaEntity r ORDER BY r.fechaReserva DESC", 
                             ReservaEntity.class)
                 .getResultList();
    }
}
