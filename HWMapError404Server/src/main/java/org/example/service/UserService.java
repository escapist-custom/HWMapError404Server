package org.example.service;

import org.example.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User insert(User user);

    User findById(long id);
    User findByEmail(String email);

    List<User> getAll();

    User update(User user);

    void deleteById(long id);
}
