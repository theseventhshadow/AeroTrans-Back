package com.aerotrans.ms_aerotrans_transfers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String clienteOid;
    private String origen;
    private String destino;
    private Long zonaId;
    private Long categoriaId;
    private LocalDateTime fechaServicio;

    @Enumerated(EnumType.STRING)
    private TransferStatus estado;

    private BigDecimal total;

    protected Transfer() {
    }

    public Transfer(String codigo, String clienteOid, String origen, String destino,
                    Long zonaId, Long categoriaId, LocalDateTime fechaServicio,
                    TransferStatus estado, BigDecimal total) {
        this.codigo = codigo;
        this.clienteOid = clienteOid;
        this.origen = origen;
        this.destino = destino;
        this.zonaId = zonaId;
        this.categoriaId = categoriaId;
        this.fechaServicio = fechaServicio;
        this.estado = estado;
        this.total = total;
    }

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getClienteOid() { return clienteOid; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public Long getZonaId() { return zonaId; }
    public Long getCategoriaId() { return categoriaId; }
    public LocalDateTime getFechaServicio() { return fechaServicio; }
    public TransferStatus getEstado() { return estado; }
    public BigDecimal getTotal() { return total; }
    public void setEstado(TransferStatus estado) { this.estado = estado; }
}