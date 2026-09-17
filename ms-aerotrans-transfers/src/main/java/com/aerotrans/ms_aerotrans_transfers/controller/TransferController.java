package com.aerotrans.ms_aerotrans_transfers.controller;

import com.aerotrans.ms_aerotrans_transfers.dto.TransferRequest;
import com.aerotrans.ms_aerotrans_transfers.dto.TransferResponse;
import com.aerotrans.ms_aerotrans_transfers.service.TransferService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping
    public List<TransferResponse> list(@RequestParam(required = false, name = "cliente_oid") String clienteOid) {
        return transferService.findAll(clienteOid);
    }

    @GetMapping("/{id}")
    public TransferResponse findById(@PathVariable Long id) {
        return transferService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransferResponse create(@Valid @RequestBody TransferRequest request) {
        return transferService.create(request);
    }

    @PutMapping("/{id}/status")
    public TransferResponse updateStatus(@PathVariable Long id, @RequestParam String status) {
        return transferService.updateStatus(id, status);
    }
}