package com.Agencia.reservas.repository;

import com.Agencia.reservas.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h FROM Hotel h WHERE disponibilidad = true")
    List<Hotel> findAllByIsAvailable();

    @Query("SELECT COUNT(id) > 0 FROM Hotel WHERE id = ?1 AND disponibilidad = true")
    boolean isAvailable(Long id);
}