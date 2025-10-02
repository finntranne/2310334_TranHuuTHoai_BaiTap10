package com.example.demo.Service;

import com.example.demo.Entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface VideoService {
    List<Video> findAll();
    Page<Video> findAll(Pageable pageable);
    Optional<Video> findById(int id);
    Video save(Video video);
    void deleteById(int id);

    // Các phương thức tìm kiếm
    List<Video> findByTitleContaining(String title);
    Page<Video> findByTitleContaining(String title, Pageable pageable);
    List<Video> findByCategory_CategoryNameContaining(String categoryName);
    Page<Video> findByCategory_CategoryNameContaining(String categoryName, Pageable pageable);
    List<Video> findByCategoryId(int categoryId);
   }