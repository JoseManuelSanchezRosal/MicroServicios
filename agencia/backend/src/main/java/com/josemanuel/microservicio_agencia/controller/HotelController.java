package com.josemanuel.microservicio_agencia.controller;

import com.josemanuel.microservicio_agencia.dto.MessageDTO;
import com.josemanuel.microservicio_agencia.model.Hotel;
import com.josemanuel.microservicio_agencia.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoteles")
@CrossOrigin
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("")
    public ResponseEntity<List<Hotel>> getAll() {
        return ResponseEntity
                .ok(this.hotelRepository.findAllByIsAvailable());
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Hotel hotel) {
        Long hotelId = hotel.getId();
        if (hotelId != null && this.hotelRepository.existsById(hotelId)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageDTO("El hotel ya está creado"));
        }
        hotel.setDisponibilidad(true);
        return ResponseEntity
                .ok(this.hotelRepository.saveAndFlush(hotel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> update(@PathVariable long id, @RequestBody Hotel hotel) {
        if (!this.hotelRepository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO("El hotel introducido no existe"));
        }
        hotel.setId(id);
        this.hotelRepository.saveAndFlush(hotel);
        return ResponseEntity
                .ok(new MessageDTO("Hotel actualizado correctamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable long id) {
        if (!this.hotelRepository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO("El hotel introducido no existe"));
        }
        Hotel hotel = new Hotel();
        hotel.setId(id);
        this.hotelRepository.delete(hotel);
        return ResponseEntity
                .ok(new MessageDTO("Hotel eliminado correctamente"));
    }
}