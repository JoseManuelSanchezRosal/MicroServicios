package com.josemanuel.microservicio_agencia.dto;

import com.josemanuel.microservicio_agencia.model.Reserva;

public record ReservaDTO(
        Reserva reserva,
        Long idVuelo,
        Long idHotel
) {}