package com.example.demo.Repository;

import com.example.demo.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    // Tìm kiếm theo tiêu đề video
    List<Video> findByTitleContaining(String title);

    // Tìm kiếm và Phân trang theo tiêu đề video
    Page<Video> findByTitleContaining(String title, Pageable pageable);

    // Tìm kiếm video theo Category (sử dụng tên thuộc tính category trong entity Video)
    List<Video> findByCategory_CategoryNameContaining(String categoryName);
    Page<Video> findByCategory_CategoryNameContaining(String categoryName, Pageable pageable);
    List<Video> findByCategoryCategoryID(int categoryId);
    
    @Query("SELECT v FROM Video v WHERE v.category.categoryID = :categoryId")
    Page<Video> findByCategoryId(@Param("categoryId") int categoryId, Pageable pageable);
}
