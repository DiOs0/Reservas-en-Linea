package com.uce.logic.usercases;

import com.uce.data.entities.ReservaEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class EditReservationUserCase {

    @Inject
    private EntityManager em;

    public ReservaEntity buscarPorId(String id) {
        return em.find(ReservaEntity.class, id);
    }

    // Guardamos los cambios
    public void actualizarReserva(ReservaEntity reserva) {   
        em.getTransaction().begin();
        em.merge(reserva); 
        em.getTransaction().commit();
    }
}
