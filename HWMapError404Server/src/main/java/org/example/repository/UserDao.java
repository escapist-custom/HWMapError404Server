package org.example.repository;

import org.example.domain.Route;
import org.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<Route> findByRouteId(long id);
}
