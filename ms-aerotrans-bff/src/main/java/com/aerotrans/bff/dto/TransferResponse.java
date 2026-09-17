package com.aerotrans.bff.dto;

import java.math.BigDecimal;

public class TransferResponse {
    private Long id;
    private String codigo;
    private String clienteOid;
    private String origen;
    private String destino;
    private Long zonaId;
    private Long categoriaId;
    private String fechaServicio;
    private String estado;
    private BigDecimal total;

    public TransferResponse() {
    }

    public TransferResponse(Long id, String codigo, String clienteOid, String origen, String destino,
                           Long zonaId, Long categoriaId, String fechaServicio, String estado, BigDecimal total) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClienteOid() {
        return clienteOid;
    }

    public void setClienteOid(String clienteOid) {
        this.clienteOid = clienteOid;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Long getZonaId() {
        return zonaId;
    }

    public void setZonaId(Long zonaId) {
        this.zonaId = zonaId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(String fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
