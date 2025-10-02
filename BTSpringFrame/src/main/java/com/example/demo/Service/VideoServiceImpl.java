package com.example.demo.Service;

import com.example.demo.Entity.Video;
import com.example.demo.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public Page<Video> findAll(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    @Override
    public Optional<Video> findById(int id) {
        return videoRepository.findById(id);
    }
    
    @Override
    public List<Video> findByCategoryId(int categoryId) {
        return videoRepository.findByCategoryCategoryID(categoryId);
    }

    @Override
    public Video save(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public void deleteById(int id) {
        videoRepository.deleteById(id);
    }

    @Override
    public List<Video> findByTitleContaining(String title) {
        return videoRepository.findByTitleContaining(title);
    }

    @Override
    public Page<Video> findByTitleContaining(String title, Pageable pageable) {
        return videoRepository.findByTitleContaining(title, pageable);
    }

    @Override
    public List<Video> findByCategory_CategoryNameContaining(String categoryName) {
        return videoRepository.findByCategory_CategoryNameContaining(categoryName);
    }

    @Override
    public Page<Video> findByCategory_CategoryNameContaining(String categoryName, Pageable pageable) {
        return videoRepository.findByCategory_CategoryNameContaining(categoryName, pageable);
    }
}
