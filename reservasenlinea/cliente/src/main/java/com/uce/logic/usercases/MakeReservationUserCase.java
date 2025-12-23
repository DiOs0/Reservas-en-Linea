package com.uce.logic.usercases;



import java.sql.Date;
import com.uce.data.entities.ReservaEntity;

import com.uce.logic.validators.UUIDReservers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;





@ApplicationScoped
public class MakeReservationUserCase {


    @Inject
    private EntityManager em;


    public String makeReservation(String userName,Date fechaReserva,Integer numeroComensales){
        
        ReservaEntity reserva= ReservaEntity.builder()
                        .reservaId(UUIDReservers.generateUUID())
                                .userName(userName)
                                .fechaReserva(fechaReserva)
                                .estadoReserva("Pendiente")
                                .mesaReservada(-1)
                                .numeroComensales(numeroComensales).build();


        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
        
        //Si tengo base de datos, ingresaria en mi bdd, y me retornaria el reservaID
        return reserva.getReservaId();
    }




}
