package com.Agencia.reservas.repository;

import com.Agencia.reservas.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    @Query("SELECT v FROM Vuelo v WHERE plazasDisponibles > 0")
    List<Vuelo> findAllByIsAvailable();

    @Query("SELECT COUNT(id) > 0 FROM Vuelo WHERE id = ?1 AND plazasDisponibles > 0")
    boolean isAvailable(Long id);
}