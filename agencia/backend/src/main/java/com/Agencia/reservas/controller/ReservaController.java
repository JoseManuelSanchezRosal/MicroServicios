package com.Agencia.reservas.controller;

import com.Agencia.reservas.dto.MessageDTO;
import com.Agencia.reservas.dto.ReservaDTO;
import com.Agencia.reservas.model.Hotel;
import com.Agencia.reservas.model.Reserva;
import com.Agencia.reservas.model.Vuelo;
import com.Agencia.reservas.repository.HotelRepository;
import com.Agencia.reservas.repository.ReservaRepository;
import com.Agencia.reservas.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin
public class ReservaController {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("")
    public ResponseEntity<List<Reserva>> getAll() {
        return ResponseEntity
                .ok(this.reservaRepository.findAll());
    }

    @PostMapping("")
    public ResponseEntity<Object> reservar(@RequestBody ReservaDTO reservaDto) {
        Long hotelId = reservaDto.idHotel();
        Optional<Hotel> hotelOptional = this.hotelRepository.findById(reservaDto.idHotel());
        if (hotelOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO("El hotel no existe"));
        }
        if (!this.hotelRepository.isAvailable(hotelId)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO("El hotel introducido no está disponible"));
        }

        Long vueloId = reservaDto.idVuelo();
        Optional<Vuelo> vueloOptional = this.vueloRepository.findById(vueloId);
        if (vueloOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO("El vuelo no existe"));
        }
        if (!this.vueloRepository.isAvailable(vueloId)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO("El vuelo introducido no está disponible"));
        }

        Hotel hotel = hotelOptional.get();
        hotel.setDisponibilidad(false);
        this.hotelRepository.saveAndFlush(hotel);

        Vuelo vuelo = vueloOptional.get();
        vuelo.setPlazasDisponibles(vuelo.getPlazasDisponibles() - 1);
        this.vueloRepository.saveAndFlush(vuelo);

        Reserva reserva = reservaDto.reserva();
        reserva.setVueloAsociado(vuelo);
        reserva.setHotelAsociado(hotel);

        return ResponseEntity
                .ok(this.reservaRepository.saveAndFlush(reserva));
    }
}