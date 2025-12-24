package com.uce.controllers.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.uce.data.entities.ReservaEntity;
import com.uce.logic.usercases.CancelReservationUserCase;
import com.uce.logic.usercases.EditReservationUserCase;
import com.uce.logic.usercases.ListReservationsUserCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AdminReservationService {

    @Inject
    private ListReservationsUserCase listUseCase;

     @Inject
    private CancelReservationUserCase cancelUseCase;

    @Inject
    private EditReservationUserCase editUseCase;

    public void mostrarReporteReservas() {
        List<ReservaEntity> reservas = listUseCase.obtenerTodasLasReservas();

        if (reservas.isEmpty()) {
            System.out.println("\n[SISTEMA ADMIN] No existen reservas registradas en el sistema.");
        } else {
            System.out.println("\n=================================================================================");
            System.out.println("                      LISTADO GENERAL DE RESERVAS - UCE                          ");
            System.out.println("=================================================================================");
            System.out.printf("%-15s | %-20s | %-12s | %-5s | %-10s%n", 
                              "FECHA", "CLIENTE", "ID CORTO", "MESA", "ESTADO");
            System.out.println("---------------------------------------------------------------------------------");

            for (ReservaEntity r : reservas) {
                // Tomamos solo los primeros 8 caracteres del UUID para que quepa en la tabla
                String idCorto = r.getReservaId().substring(0, 8);
                
                System.out.printf("%-15s | %-20s | %-12s | %-5s | %-10s%n", 
                                  r.getFechaReserva().toString(),
                                  r.getUserName(),
                                  idCorto,
                                  r.getMesaReservada(),
                                  r.getEstadoReserva());
            }
            System.out.println("=================================================================================");
            System.out.println("Total de reservas encontradas: " + reservas.size());
        }
    }



    public void procesarCancelacion(String id) {
        System.out.println("\n[SISTEMA ADMIN] Procesando cancelación del ID: " + id);
        
        boolean exito = cancelUseCase.ejecutarCancelacion(id);

        if (exito) {
            System.out.println("ÉXITO: La reserva ha sido marcada como 'Cancelada'.");
        } else {
            System.out.println("ERROR: No se encontró ninguna reserva con ese ID.");
        }
    }


    public void procesarEdicion(String id, Scanner scanner) {
        ReservaEntity reserva = editUseCase.buscarPorId(id);

        if (reserva == null) {
            System.out.println("ERROR: No se encontró la reserva con ID: " + id);
            return;
        }

        System.out.println("\n--- EDITANDO RESERVA (Deje vacío para mantener valor actual) ---");
        SimpleDateFormat sdf = new SimpleDateFormat("(yy-MM-dd)");

        try {            
            System.out.print("Número de comensales actual [" + reserva.getNumeroComensales() + "]: ");
            String inputCom = scanner.nextLine();
            if (!inputCom.isEmpty()) reserva.setNumeroComensales(Integer.parseInt(inputCom));

            
            System.out.print("Mesa asignada actual [" + reserva.getMesaReservada() + "]: ");
            String inputMesa = scanner.nextLine();
            if (!inputMesa.isEmpty()) reserva.setMesaReservada(Integer.parseInt(inputMesa));

            
            System.out.print("Fecha actual [" + sdf.format(reserva.getFechaReserva()) + "] (yy-MM-dd): ");
            String inputFecha = scanner.nextLine();
            LocalDate localDate = LocalDate.parse(inputFecha);
            Date fecha = Date.valueOf(localDate);
            if (!inputFecha.isEmpty()) reserva.setFechaReserva(fecha);

            
            editUseCase.actualizarReserva(reserva);
            System.out.println("ÉXITO: Reserva actualizada correctamente.");

        } catch (Exception e) {
            System.out.println("ERROR: Formato de dato incorrecto. No se guardaron cambios.");
        }
    }

}
