package com.aerotrans.bff.dto;

public class CategoryResponse {
    private Long id;
    private String nombre;
    private Integer capacidadPasajeros;
    private Integer capacidadMaletas;

    public CategoryResponse() {
    }

    public CategoryResponse(Long id, String nombre, Integer capacidadPasajeros, Integer capacidadMaletas) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadMaletas = capacidadMaletas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
