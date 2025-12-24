package com.uce.data.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


@ApplicationScoped
public class DbConfig {

    // El nombre debe ser igual al "persistence-unit name" del paso 2
    private static final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("ReservasPU");

    @Produces
    @ApplicationScoped
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    // Esto cierra la conexión automáticamente cuando la app se detiene
    public void close(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}
