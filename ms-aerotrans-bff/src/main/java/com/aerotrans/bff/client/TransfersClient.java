package com.aerotrans.bff.client;

import com.aerotrans.bff.dto.TransferRequest;
import com.aerotrans.bff.dto.TransferResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TransfersClient {

    private final WebClient webClient;

    public TransfersClient(@Qualifier("transfersWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<TransferResponse> listTransfers(String clienteOid) {
        return webClient.get()
            .uri(uriBuilder -> {
                uriBuilder.path("/api/transfers");
                if (clienteOid != null && !clienteOid.isBlank()) {
                    uriBuilder.queryParam("cliente_oid", clienteOid);
                }
                return uriBuilder.build();
            })
            .retrieve()
            .bodyToFlux(TransferResponse.class);
    }

    public Mono<TransferResponse> getTransfer(Long id) {
        return webClient.get()
            .uri("/api/transfers/{id}", id)
            .retrieve()
            .bodyToMono(TransferResponse.class);
    }

    public Mono<TransferResponse> createTransfer(TransferRequest request) {
        return webClient.post()
            .uri("/api/transfers")
            .bodyValue(request)
            .retrieve()
            .bodyToMono(TransferResponse.class);
    }

    public Mono<TransferResponse> updateStatus(Long id, String status) {
        return webClient.put()
            .uri(uriBuilder -> uriBuilder
                .path("/api/transfers/{id}/status")
                .queryParam("status", status)
                .build(id))
            .retrieve()
            .bodyToMono(TransferResponse.class);
    }
}
