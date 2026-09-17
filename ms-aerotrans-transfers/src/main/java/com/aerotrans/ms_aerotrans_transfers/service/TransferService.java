package com.aerotrans.ms_aerotrans_transfers.service;

import com.aerotrans.ms_aerotrans_transfers.dto.TransferRequest;
import com.aerotrans.ms_aerotrans_transfers.dto.TransferResponse;
import com.aerotrans.ms_aerotrans_transfers.exception.InvalidTransferStatusException;
import com.aerotrans.ms_aerotrans_transfers.exception.ResourceNotFoundException;
import com.aerotrans.ms_aerotrans_transfers.model.Transfer;
import com.aerotrans.ms_aerotrans_transfers.model.TransferStatus;
import com.aerotrans.ms_aerotrans_transfers.repository.TransferRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TransferService {

    private final TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public List<TransferResponse> findAll(String clienteOid) {
        List<Transfer> transfers = clienteOid == null || clienteOid.isBlank()
                ? transferRepository.findAll()
                : transferRepository.findByClienteOid(clienteOid);
        return transfers.stream().map(this::toResponse).toList();
    }

    public TransferResponse findById(Long id) {
        return toResponse(transferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found: " + id)));
    }

    @Transactional
    public TransferResponse create(TransferRequest request) {
        Transfer transfer = new Transfer(
                generateCode(),
                request.clienteOid(),
                request.origen(),
                request.destino(),
                request.zonaId(),
                request.categoriaId(),
                request.fechaServicio(),
                TransferStatus.PENDIENTE,
                BigDecimal.ZERO);
        return toResponse(transferRepository.save(transfer));
    }

    @Transactional
    public TransferResponse updateStatus(Long id, String status) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found: " + id));
        TransferStatus newStatus;
        try {
            newStatus = TransferStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new InvalidTransferStatusException("Invalid transfer status: " + status);
        }
        if (transfer.getEstado() == TransferStatus.COMPLETADO
                || transfer.getEstado() == TransferStatus.CANCELADO) {
            throw new InvalidTransferStatusException("Transfer cannot change from " + transfer.getEstado());
        }
        transfer.setEstado(newStatus);
        return toResponse(transfer);
    }

    private String generateCode() {
        return "TR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private TransferResponse toResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getId(),
                transfer.getCodigo(),
                transfer.getClienteOid(),
                transfer.getOrigen(),
                transfer.getDestino(),
                transfer.getZonaId(),
                transfer.getCategoriaId(),
                transfer.getFechaServicio(),
                transfer.getEstado().name(),
                transfer.getTotal());
    }
}