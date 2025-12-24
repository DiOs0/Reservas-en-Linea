
package com.uce;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


import com.uce.controllers.services.AvailabilityService;
import com.uce.controllers.services.MakeReservationService;
import com.uce.controllers.services.NotificationService;
import com.uce.data.entities.ReservaEntity;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class UserApp {
    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            
            // Obtenemos los servicios necesarios
            AvailabilityService availabilityService = container.select(AvailabilityService.class).get();
            MakeReservationService reservationService = container.select(MakeReservationService.class).get();
            NotificationService notificationService = container.select(NotificationService.class).get();
            
            Scanner scanner = new Scanner(System.in);

            System.out.println("======= BIENVENIDO A RESERVAS UCE =======");

            // PASO 1: Ingreso de Fecha y Consulta de Disponibilidad
            System.out.print("Ingrese la fecha para su reserva (yyyy-MM-dd/): ");
            String fechaStr = scanner.nextLine();
            LocalDate localDate = LocalDate.parse(fechaStr);
            Date fecha = Date.valueOf(localDate);

            List<Integer> disponibles = availabilityService.obtenerMesasLibres(fecha);

            if (disponibles.isEmpty()) {
                System.out.println("Lo sentimos, no hay mesas disponibles para esa fecha.");
                return; // Finaliza el programa si no hay cupo
            }

            System.out.println("\nMesas disponibles para esa fecha:");
            availabilityService.consultarDisponibilidad(fecha);

            // PASO 2: Selección de Mesa
            System.out.print("\nElija el número de mesa que desea: ");
            int mesaSeleccionada = Integer.parseInt(scanner.nextLine());

            if (!disponibles.contains(mesaSeleccionada)) {
                System.out.println("Error: Esa mesa no está disponible o no existe.");
                return;
            }

            // PASO 3: Ingreso de datos personales (Login/Registro)
            System.out.println("\n--- Por favor, ingrese sus datos para confirmar ---");
            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();

            System.out.print("Correo electrónico: ");
            String email = scanner.nextLine();

            System.out.print("Número de comensales: ");
            int comensales = Integer.parseInt(scanner.nextLine());

            // PASO 4: Ejecución del Flujo Final (Guardado + Notificación)
            System.out.println("\nProcesando su solicitud...");
            
            String id=reservationService.makeReservation(
                nombre, 
                fecha, 
                comensales, 
                mesaSeleccionada, 
                email
            );
            ReservaEntity reserva = ReservaEntity.builder()
                                        .userName(nombre)
                                        .fechaReserva(fecha)
                                        .mesaReservada(mesaSeleccionada)
                                        .numeroComensales(comensales)
                                        .emailUser(email)
                                        .build();

            String res=notificationService.procesarNotificacion(reserva,id);
            System.out.println(res);

            System.out.println("\n¡Gracias por preferirnos!");

        } catch (Exception e) {
            System.err.println("\nOcurrió un error: " + e.getMessage());
            System.out.println("Asegúrese de ingresar los datos en el formato correcto.");
        }
    }
}
