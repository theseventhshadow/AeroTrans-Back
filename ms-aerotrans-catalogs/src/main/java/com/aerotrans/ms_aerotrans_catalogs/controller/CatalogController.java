package com.aerotrans.ms_aerotrans_catalogs.controller;

import com.aerotrans.ms_aerotrans_catalogs.dto.CategoryRequest;
import com.aerotrans.ms_aerotrans_catalogs.dto.CategoryResponse;
import com.aerotrans.ms_aerotrans_catalogs.dto.ZoneRequest;
import com.aerotrans.ms_aerotrans_catalogs.dto.ZoneResponse;
import com.aerotrans.ms_aerotrans_catalogs.service.CategoryService;
import com.aerotrans.ms_aerotrans_catalogs.service.ZoneService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/zones/{id}")
    public ZoneResponse findZone(@PathVariable Long id) {
        return zoneService.findById(id);
    }

    @PostMapping("/zones")
    @ResponseStatus(HttpStatus.CREATED)
    public ZoneResponse createZone(@Valid @RequestBody ZoneRequest request) {
        return zoneService.create(request);
    }

    @PutMapping("/zones/{id}")
    public ZoneResponse updateZone(@PathVariable Long id, @Valid @RequestBody ZoneRequest request) {
        return zoneService.update(id, request);
    }

    @DeleteMapping("/zones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteZone(@PathVariable Long id) {
        zoneService.delete(id);
    }

    @GetMapping("/categories")
    public List<CategoryResponse> listCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public CategoryResponse findCategory(@PathVariable Long id) {
        return categoryService.findById(id);
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

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}