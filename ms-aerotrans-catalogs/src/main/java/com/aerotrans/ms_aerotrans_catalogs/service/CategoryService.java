package com.aerotrans.ms_aerotrans_catalogs.service;

import com.aerotrans.ms_aerotrans_catalogs.dto.CategoryRequest;
import com.aerotrans.ms_aerotrans_catalogs.dto.CategoryResponse;
import com.aerotrans.ms_aerotrans_catalogs.exception.ResourceNotFoundException;
import com.aerotrans.ms_aerotrans_catalogs.model.Category;
import com.aerotrans.ms_aerotrans_catalogs.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        Category category = new Category(
                request.nombre(),
                request.capacidadPasajeros(),
                request.capacidadMaletas());
        return toResponse(categoryRepository.save(category));
    }

    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
        category.setNombre(request.nombre());
        category.setCapacidadPasajeros(request.capacidadPasajeros());
        category.setCapacidadMaletas(request.capacidadMaletas());
        return toResponse(category);
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getNombre(),
                category.getCapacidadPasajeros(),
                category.getCapacidadMaletas());
    }
}