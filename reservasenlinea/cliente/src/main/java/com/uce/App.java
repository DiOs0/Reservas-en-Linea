
package com.uce;
import java.sql.Date;
import java.util.UUID;

import com.uce.controllers.services.AvailabilityService;
import com.uce.controllers.services.MakeReservationService;
import com.uce.controllers.services.NotificationService;
import com.uce.data.entities.ReservaEntity;
import com.uce.logic.usercases.MakeReservationUserCase;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class App {

    public static void main(String[] args) throws InterruptedException {



        //MI IDEA ES HACER UN MENU SIMPLEMENTE PARA PROBAR CADA CDU
        //es decir del 1 al 11 de los casos de uso, e ingresa todo quemado los datos y listo 


        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {


            //Creamos el usuario/entidad reserva
            ReservaEntity usuario_reserva = ReservaEntity.builder()
                    .userName("Joel Aaron Ledesma")
                    .fechaReserva(new Date(System.currentTimeMillis()))
                    .numeroComensales(2)
                    .mesaReservada(5)
                    .emailUser("carlos@example.com")
                    .build();
            
            
            

            //Usando el CDU01 – Consultar disponibilidad de mesas
            AvailabilityService service = container.select(AvailabilityService.class).get();
            Date fechaConsulta = new Date(System.currentTimeMillis()); 
            
            System.out.println("=== CONSULTANDO DISPONIBILIDAD ===");
            service.consultarDisponibilidad(fechaConsulta);


            //Usando el Make reservation service para crear una reserva en la bdd (CDU02 – Realizar reserva online)
            MakeReservationService app = container.select(MakeReservationService.class).get();
            String id = app.makeReservation(usuario_reserva.getUserName(),
                                            usuario_reserva.getFechaReserva(),
                                            usuario_reserva.getNumeroComensales(),
                                            usuario_reserva.getMesaReservada(),
                                            usuario_reserva.getEmailUser()
                                            );
            System.out.println("Reserva creada con ID: " + id);



            //Usando el CDU03 – Recibir confirmación de reserva por correo
            NotificationService notificationService = container.select(NotificationService.class).get();

            System.out.println("=== INICIANDO CDU03 ===");
            String resultado = notificationService.procesarNotificacion(usuario_reserva,id);
            System.out.println(resultado);



        }

    }
}
