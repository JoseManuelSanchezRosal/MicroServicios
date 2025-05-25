package com.josemanuel.microservicio_agencia.controller;

import com.josemanuel.microservicio_agencia.dto.MessageDTO;
import com.josemanuel.microservicio_agencia.model.Vuelo;
import com.josemanuel.microservicio_agencia.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
@CrossOrigin
public class VueloController {
    @Autowired
    private VueloRepository vueloRepository;

    @GetMapping("")
    public ResponseEntity<List<Vuelo>> getAll() {
        return ResponseEntity
                .ok(this.vueloRepository.findAllByIsAvailable());
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Vuelo vuelo) {
        Long vueloId = vuelo.getId();
        if (vueloId != null && this.vueloRepository.existsById(vueloId)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageDTO("El vuelo ya está creado"));
        }
        if (vuelo.getPlazasDisponibles() < 1) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO("El vuelo debe contener al menos 1 plaza"));
        }
        return ResponseEntity
                .ok(this.vueloRepository.saveAndFlush(vuelo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> update(@PathVariable long id, @RequestBody Vuelo vuelo) {
        if (!this.vueloRepository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO("El vuelo introducido no existe"));
        }
        vuelo.setId(id);
        this.vueloRepository.saveAndFlush(vuelo);
        return ResponseEntity
                .ok(new MessageDTO("Vuelo actualizado correctamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable long id) {
        if (!this.vueloRepository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO("El vuelo introducido no existe"));
        }
        Vuelo vuelo = new Vuelo();
        vuelo.setId(id);
        this.vueloRepository.delete(vuelo);
        return ResponseEntity
                .ok(new MessageDTO("Vuelo eliminado correctamente"));
    }
}