package com.uce;




import java.sql.Date;
import java.util.Scanner;

import com.uce.controllers.services.AdminReservationService;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class AdminApp {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            
            AdminReservationService adminService = container.select(AdminReservationService.class).get();


            Scanner scanner = new Scanner(System.in);
            int opcion = 0;

            do {
                System.out.println("\n===== PANEL DE ADMINISTRACIÓN - RESERVAS =====");
                System.out.println("1. Consultar Listado de Reservas");
                System.out.println("2. Cancelar una Reserva por ID");
                System.out.println("3. Editar una Reserva por ID");
                System.out.println("4. Consultar las Fechas disponibles");
                System.out.println("5. Ingresar una Reserva Manualmente");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        adminService.mostrarReporteReservas();
                        break;
                    case 2:
                        System.out.print("\nIngrese el ID completo de la reserva a cancelar: ");
                        String id = scanner.nextLine();
                        adminService.procesarCancelacion(id);
                        break;
                    case 3:
                        System.out.print("\nIngrese el ID de la reserva a editar: ");
                        adminService.procesarEdicion(scanner.nextLine(), scanner);
                        break;
                    case 4:
                        System.out.print("\nIngrese la fecha a consultar (yyyy-MM-dd): ");
                        try {
                            String fStr = scanner.nextLine();
                            java.sql.Date f = java.sql.Date.valueOf(fStr);
                            adminService.mostrarDisponibilidadTotal(f);
                        } catch (Exception e) {
                            System.out.println("❌ Formato de fecha inválido.");
                        }
                        break;
                    case 5:
                        adminService.procesarReservaManual(scanner);
                        break;
                    case 6:
                        System.out.println("Saliendo del panel de administración. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 6);



        } catch (Exception e) {
            System.err.println("Error en el módulo de administración: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
