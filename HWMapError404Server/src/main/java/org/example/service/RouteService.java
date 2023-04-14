package org.example.service;

import org.example.domain.Route;
import org.example.domain.User;

import java.util.List;

public interface RouteService {
    Route insert(Route route);

    Route findById(long id);

    Route findByName(String name);

    List<Route> getAll();

    Route update(Route route);

    void deleteById(long id);
}
