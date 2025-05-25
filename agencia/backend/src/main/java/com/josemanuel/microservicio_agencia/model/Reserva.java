package com.josemanuel.microservicio_agencia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String dni;

    @ManyToOne
    @JoinColumn(name = "vuelo_asociado", nullable = false)
    private Vuelo vueloAsociado;

    @ManyToOne
    @JoinColumn(name = "hotel_asociado", nullable = false)
    private Hotel hotelAsociado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Vuelo getVueloAsociado() {
        return vueloAsociado;
    }

    public void setVueloAsociado(Vuelo vueloAsociado) {
        this.vueloAsociado = vueloAsociado;
    }

    public Hotel getHotelAsociado() {
        return hotelAsociado;
    }

    public void setHotelAsociado(Hotel hotelAsociado) {
        this.hotelAsociado = hotelAsociado;
    }
}