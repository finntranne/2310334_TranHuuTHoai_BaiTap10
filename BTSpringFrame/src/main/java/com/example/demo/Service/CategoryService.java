package com.example.demo.Service;

import com.example.demo.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findById(int id);
    Category save(Category category);
    void deleteById(int id);
    
    // Các phương thức tìm kiếm
    List<Category> findByCategoryNameContaining(String name);
    Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
}
