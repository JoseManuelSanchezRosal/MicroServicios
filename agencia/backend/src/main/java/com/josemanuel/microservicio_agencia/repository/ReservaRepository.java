package com.josemanuel.microservicio_agencia.repository;

import com.josemanuel.microservicio_agencia.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}