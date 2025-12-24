package com.uce.logic.usercases;

import java.sql.Date;

import com.uce.data.entities.ReservaEntity;
import com.uce.logic.validators.UUIDReservers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ManualReservationUserCase {

    @Inject
    private EntityManager em;

    

    public ReservaEntity registrarReserva(String nombre, Date fecha, int mesa, int comensales, String email) {
        
        

        ReservaEntity nuevaReserva = ReservaEntity.builder()
                .reservaId(UUIDReservers.generateUUID())
                .userName(nombre)
                .fechaReserva(fecha)
                .estadoReserva("CONFIRMADA_ADMIN") // Marcamos que fue hecha por un admin
                .mesaReservada(mesa)
                .numeroComensales(comensales)
                .emailUser(email)
                .build();

        // 3. Persistencia
        em.getTransaction().begin();
        em.persist(nuevaReserva);
        em.getTransaction().commit();

        return nuevaReserva;
    }
}
