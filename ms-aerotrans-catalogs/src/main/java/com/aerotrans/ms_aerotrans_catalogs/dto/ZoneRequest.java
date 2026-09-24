package com.aerotrans.ms_aerotrans_catalogs.dto;

import jakarta.validation.constraints.NotBlank;

public record ZoneRequest(
        @NotBlank String nombre,
        @NotBlank String comuna) {
}