package com.aerotrans.bff.dto;

public class ZoneResponse {
    private Long id;
    private String nombre;
    private String comuna;

    public ZoneResponse() {
    }

    public ZoneResponse(Long id, String nombre, String comuna) {
        this.id = id;
        this.nombre = nombre;
        this.comuna = comuna;
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

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
}
