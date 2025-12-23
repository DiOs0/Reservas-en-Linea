
package com.uce;
import java.sql.Date;

import com.uce.controllers.services.MakeReservationService;
import com.uce.logic.usercases.MakeReservationUserCase;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class App {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            
            // Obtenemos el servicio desde el contenedor
            MakeReservationService app = container.select(MakeReservationService.class).get();
            

            //Usando el Make reservation service para crear una reserva en la bdd
            String id = app.makeReservation("Carlos", new Date(System.currentTimeMillis()), 4);
            System.out.println("Reserva creada con ID: " + id);

                


        }


        /* 
        MakeReservationService service= new MakeReservationService(new MakeReservationUserCase());


        String reservaId=service.makeReservation("Carlos", new Date(System.currentTimeMillis()), 4);

        System.out.println(reservaId);
        */

    }
}
