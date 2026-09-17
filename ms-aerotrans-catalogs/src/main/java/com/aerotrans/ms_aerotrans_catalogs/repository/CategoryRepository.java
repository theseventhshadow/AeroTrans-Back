package com.aerotrans.ms_aerotrans_catalogs.repository;

import com.aerotrans.ms_aerotrans_catalogs.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}