package com.aerotrans.bff.client;

import com.aerotrans.bff.dto.CategoryResponse;
import com.aerotrans.bff.dto.ZoneResponse;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CatalogClient {

    private final WebClient webClient;

    public CatalogClient(@Qualifier("catalogWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<ZoneResponse> listZones() {
        return webClient.get()
            .uri("/api/catalog/zones")
            .retrieve()
            .bodyToFlux(ZoneResponse.class);
    }

    public Mono<ZoneResponse> getZone(Long id) {
        return webClient.get()
            .uri("/api/catalog/zones/{id}", id)
            .retrieve()
            .bodyToMono(ZoneResponse.class);
    }

    public Mono<ZoneResponse> createZone(Map<String, Object> payload) {
        return webClient.post()
            .uri("/api/catalog/zones")
            .bodyValue(payload)
            .retrieve()
            .bodyToMono(ZoneResponse.class);
    }

    public Mono<ZoneResponse> updateZone(Long id, Map<String, Object> payload) {
        return webClient.put()
            .uri("/api/catalog/zones/{id}", id)
            .bodyValue(payload)
            .retrieve()
            .bodyToMono(ZoneResponse.class);
    }

    public Mono<Void> deleteZone(Long id) {
        return webClient.delete()
            .uri("/api/catalog/zones/{id}", id)
            .retrieve()
            .bodyToMono(Void.class);
    }

    public Flux<CategoryResponse> listCategories() {
        return webClient.get()
            .uri("/api/catalog/categories")
            .retrieve()
            .bodyToFlux(CategoryResponse.class);
    }

    public Mono<CategoryResponse> getCategory(Long id) {
        return webClient.get()
            .uri("/api/catalog/categories/{id}", id)
            .retrieve()
            .bodyToMono(CategoryResponse.class);
    }

    public Mono<CategoryResponse> createCategory(Map<String, Object> payload) {
        return webClient.post()
            .uri("/api/catalog/categories")
            .bodyValue(payload)
            .retrieve()
            .bodyToMono(CategoryResponse.class);
    }

    public Mono<CategoryResponse> updateCategory(Long id, Map<String, Object> payload) {
        return webClient.put()
            .uri("/api/catalog/categories/{id}", id)
            .bodyValue(payload)
            .retrieve()
            .bodyToMono(CategoryResponse.class);
    }

    public Mono<Void> deleteCategory(Long id) {
        return webClient.delete()
            .uri("/api/catalog/categories/{id}", id)
            .retrieve()
            .bodyToMono(Void.class);
    }
}
