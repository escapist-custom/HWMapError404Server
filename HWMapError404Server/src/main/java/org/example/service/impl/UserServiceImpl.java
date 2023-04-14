package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.repository.UserDao;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User insert(User user) {
        return userDao.save(user);
    }

    @Override
    public User findById(long id) {
        if (userDao.findById(id).isPresent()) return userDao.findById(id).get();
        else return null;
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User update(User user) {
        User newUser = User.builder()
                .id(user.getId())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .password(user.getPassword())
                .routes(user.getRoutes())
                .build();
        return userDao.save(newUser);
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }
}
