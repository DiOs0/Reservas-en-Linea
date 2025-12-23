package com.uce.controllers.services;

import java.sql.Date;

import com.uce.logic.usercases.MakeReservationUserCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;



@ApplicationScoped
public class MakeReservationService {

    
    private MakeReservationUserCase userCase;

    @Inject
    public MakeReservationService(MakeReservationUserCase userCase){
        this.userCase=userCase;
    }

    public String makeReservation(String userName,Date fechaReserva,Integer numeroComensales, Integer mesaReservada,String emailUser) {
        
        return userCase.makeReservation(userName, fechaReserva, numeroComensales, mesaReservada, emailUser);
    }

}
