package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByUsernameContaining(String name) {
        return userRepository.findByUsernameContaining(name);
    }
    
    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        
        return userRepository.findByUsernameAndPassword(username, password);
    }
    @Override
    public Page<User> findByUsernameContaining(String name, Pageable pageable) {
        return userRepository.findByUsernameContaining(name, pageable);
       
    }
}