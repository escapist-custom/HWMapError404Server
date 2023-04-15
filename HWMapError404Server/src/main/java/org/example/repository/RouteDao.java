package org.example.repository;

import org.example.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteDao extends JpaRepository<Route, Long> {

    Route findByName(String nameOfRoute);
}
