package com.uce.logic.usercases;

import com.uce.data.entities.ReservaEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CancelReservationUserCase {

    @Inject
    private EntityManager em;

    public boolean ejecutarCancelacion(String reservaId) {
        ReservaEntity reserva = em.find(ReservaEntity.class, reservaId);

        if (reserva != null) {
            em.getTransaction().begin();
            
            reserva.setEstadoReserva("Cancelada");
            em.getTransaction().commit();
            return true;
        }
        
        return false; 
    }
}
