package com.aerotrans.bff.controller;

import com.aerotrans.bff.client.CatalogClient;
import com.aerotrans.bff.dto.CategoryResponse;
import com.aerotrans.bff.dto.ZoneResponse;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CatalogController {

    private final CatalogClient catalogClient;

    public CatalogController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping("/catalog/zones")
    public Flux<ZoneResponse> listZones() {
        return catalogClient.listZones();
    }

    @GetMapping("/catalog/zones/{id}")
    public Mono<ResponseEntity<ZoneResponse>> getZone(@PathVariable Long id) {
        return catalogClient.getZone(id).map(ResponseEntity::ok);
    }

    @PostMapping("/catalog/zones")
    public Mono<ResponseEntity<ZoneResponse>> createZone(@RequestBody Map<String, Object> payload) {
        return catalogClient.createZone(payload)
            .map(response -> ResponseEntity.status(201).body(response));
    }

    @PutMapping("/catalog/zones/{id}")
    public Mono<ResponseEntity<ZoneResponse>> updateZone(@PathVariable Long id,
                                                         @RequestBody Map<String, Object> payload) {
        return catalogClient.updateZone(id, payload).map(ResponseEntity::ok);
    }

    @DeleteMapping("/catalog/zones/{id}")
    public Mono<ResponseEntity<Void>> deleteZone(@PathVariable Long id) {
        return catalogClient.deleteZone(id)
            .thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping("/catalog/categories")
    public Flux<CategoryResponse> listCategories() {
        return catalogClient.listCategories();
    }

    @GetMapping("/catalog/categories/{id}")
    public Mono<ResponseEntity<CategoryResponse>> getCategory(@PathVariable Long id) {
        return catalogClient.getCategory(id).map(ResponseEntity::ok);
    }

    @PostMapping("/catalog/categories")
    public Mono<ResponseEntity<CategoryResponse>> createCategory(@RequestBody Map<String, Object> payload) {
        return catalogClient.createCategory(payload)
            .map(ResponseEntity::ok);
    }

    @PutMapping("/catalog/categories/{id}")
    public Mono<ResponseEntity<CategoryResponse>> updateCategory(@PathVariable Long id,
                                                                @RequestBody Map<String, Object> payload) {
        return catalogClient.updateCategory(id, payload)
            .map(ResponseEntity::ok);
    }

    @DeleteMapping("/catalog/categories/{id}")
    public Mono<ResponseEntity<Void>> deleteCategory(@PathVariable Long id) {
        return catalogClient.deleteCategory(id)
            .thenReturn(ResponseEntity.noContent().build());
    }
}
