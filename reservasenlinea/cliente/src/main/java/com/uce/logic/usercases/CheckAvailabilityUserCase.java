package com.uce.logic.usercases;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CheckAvailabilityUserCase {

    @Inject
    private EntityManager em;

    public List<Integer> obtenerMesasDisponibles(java.util.Date fechaReserva) {
        // Rango de mesas maximas
        List<Integer> todasLasMesas = IntStream.rangeClosed(1, 20)
                                               .boxed()
                                               .collect(Collectors.toList());

        List<Integer> mesasOcupadas = em.createQuery(
            "SELECT r.mesaReservada FROM ReservaEntity r " +
            "WHERE r.fechaReserva = :fecha AND r.estadoReserva != 'Cancelada'", 
            Integer.class)
            .setParameter("fecha", fechaReserva)
            .getResultList();

        // 3. Restamos las ocupadas de las totales para obtener las disponibles
        todasLasMesas.removeAll(mesasOcupadas);
        
        return todasLasMesas;
    }
}
