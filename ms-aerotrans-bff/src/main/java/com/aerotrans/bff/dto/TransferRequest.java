package com.aerotrans.bff.dto;

public class TransferRequest {
    private String origen;
    private String destino;
    private Long zonaId;
    private Long categoriaId;
    private String fechaServicio;

    public TransferRequest() {
    }

    public TransferRequest(String origen, String destino, Long zonaId, Long categoriaId, String fechaServicio) {
        this.origen = origen;
        this.destino = destino;
        this.zonaId = zonaId;
        this.categoriaId = categoriaId;
        this.fechaServicio = fechaServicio;
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
}
