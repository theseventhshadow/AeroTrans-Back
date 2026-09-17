package com.aerotrans.ms_aerotrans_catalogs.controller;

import com.aerotrans.ms_aerotrans_catalogs.dto.CategoryRequest;
import com.aerotrans.ms_aerotrans_catalogs.dto.CategoryResponse;
import com.aerotrans.ms_aerotrans_catalogs.dto.ZoneResponse;
import com.aerotrans.ms_aerotrans_catalogs.service.CategoryService;
import com.aerotrans.ms_aerotrans_catalogs.service.ZoneService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private final ZoneService zoneService;
    private final CategoryService categoryService;

    public CatalogController(ZoneService zoneService, CategoryService categoryService) {
        this.zoneService = zoneService;
        this.categoryService = categoryService;
    }

    @GetMapping("/zones")
    public List<ZoneResponse> listZones() {
        return zoneService.findAll();
    }

    @GetMapping("/categories")
    public List<CategoryResponse> listCategories() {
        return categoryService.findAll();
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request) {
        return categoryService.create(request);
    }

    @PutMapping("/categories/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id,
                                           @Valid @RequestBody CategoryRequest request) {
        return categoryService.update(id, request);
    }
}