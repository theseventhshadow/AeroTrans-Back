package com.aerotrans.ms_aerotrans_catalogs.dto;

public record CategoryResponse(
        Long id,
        String nombre,
        Integer capacidadPasajeros,
        Integer capacidadMaletas) {
}