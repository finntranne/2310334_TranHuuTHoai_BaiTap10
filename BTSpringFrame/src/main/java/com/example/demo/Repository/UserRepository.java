package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Tìm kiếm theo tên người dùng
    List<User> findByUsernameContaining(String name);

    // Tìm kiếm và Phân trang theo tên người dùng
    Page<User> findByUsernameContaining(String name, Pageable pageable);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
