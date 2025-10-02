package com.example.demo.Service;

import com.example.demo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(int id);
    User save(User user);
    void deleteById(int id);

    // Các phương thức tìm kiếm
    List<User> findByUsernameContaining(String name);
    Page<User> findByUsernameContaining(String name, Pageable pageable);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
