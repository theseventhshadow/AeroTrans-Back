package com.aerotrans.bff.controller;

import com.aerotrans.bff.client.TransfersClient;
import com.aerotrans.bff.dto.TransferRequest;
import com.aerotrans.bff.dto.TransferResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TransfersController {

    private final TransfersClient transfersClient;

    public TransfersController(TransfersClient transfersClient) {
        this.transfersClient = transfersClient;
    }

    @GetMapping("/transfers")
    public Flux<TransferResponse> listTransfers(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String clienteOid = extractClienteOid(jwt);
        return transfersClient.listTransfers(clienteOid);
    }

    @GetMapping("/transfers/{id}")
    public Mono<ResponseEntity<TransferResponse>> getTransfer(@PathVariable Long id) {
        return transfersClient.getTransfer(id)
            .map(ResponseEntity::ok);
    }

    @PostMapping("/transfers")
    public Mono<ResponseEntity<TransferResponse>> createTransfer(@RequestBody TransferRequest request,
                                                                  Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        request.setClienteOid(extractClienteOid(jwt));
        return transfersClient.createTransfer(request)
            .map(ResponseEntity::ok);
    }

    @PutMapping("/transfers/{id}/status")
    public Mono<ResponseEntity<TransferResponse>> updateStatus(@PathVariable Long id,
                                                            @RequestParam String status) {
        return transfersClient.updateStatus(id, status)
            .map(ResponseEntity::ok);
    }

    private String extractClienteOid(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList("roles");
        if (roles != null && roles.contains("ROLE_CLIENTE")) {
            return jwt.getClaimAsString("oid");
        }
        return null;
    }
}
