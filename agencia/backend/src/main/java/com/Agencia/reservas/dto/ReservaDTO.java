package com.Agencia.reservas.dto;

import com.Agencia.reservas.model.Reserva;

public record ReservaDTO(
        Reserva reserva,
        Long idVuelo,
        Long idHotel
) {}