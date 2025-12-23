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


    public String makeReservation(String userName,Date fechaReserva,Integer numeroComensales,Integer mesaReservada,String emailUser) {
        
        ReservaEntity reserva= ReservaEntity.builder()
                        .reservaId(UUIDReservers.generateUUID())
                                .userName(userName)
                                .fechaReserva(fechaReserva)
                                .estadoReserva("Reservada")
                                .mesaReservada(mesaReservada)
                                .numeroComensales(numeroComensales)
                                .emailUser(emailUser).build();


        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
        
        //Si tengo base de datos, ingresaria en mi bdd, y me retornaria el reservaID
        return reserva.getReservaId();
    }




}
