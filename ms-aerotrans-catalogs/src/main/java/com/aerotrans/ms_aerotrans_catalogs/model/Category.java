package com.aerotrans.ms_aerotrans_catalogs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer capacidadPasajeros;

    private Integer capacidadMaletas;

    protected Category() {
    }

    public Category(String nombre, Integer capacidadPasajeros, Integer capacidadMaletas) {
        this.nombre = nombre;
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadMaletas = capacidadMaletas;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(Integer capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public Integer getCapacidadMaletas() {
        return capacidadMaletas;
    }

    public void setCapacidadMaletas(Integer capacidadMaletas) {
        this.capacidadMaletas = capacidadMaletas;
    }
}