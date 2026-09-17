package com.aerotrans.ms_aerotrans_transfers.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferResponse(
        Long id,
        String codigo,
        String clienteOid,
        String origen,
        String destino,
        Long zonaId,
        Long categoriaId,
        LocalDateTime fechaServicio,
        String estado,
        BigDecimal total) {
}