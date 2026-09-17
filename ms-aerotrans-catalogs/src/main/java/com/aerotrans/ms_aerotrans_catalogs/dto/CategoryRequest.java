package com.aerotrans.ms_aerotrans_catalogs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CategoryRequest(
        @NotBlank String nombre,
        @NotNull @Positive Integer capacidadPasajeros,
        @NotNull @Positive Integer capacidadMaletas) {
}