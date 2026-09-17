package com.aerotrans.ms_aerotrans_transfers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TransferRequest(
        @NotBlank String origen,
        @NotBlank String destino,
        @NotNull Long zonaId,
        @NotNull Long categoriaId,
        @NotNull LocalDateTime fechaServicio,
        @NotBlank String clienteOid) {
}