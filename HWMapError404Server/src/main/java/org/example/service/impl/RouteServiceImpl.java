package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.Route;
import org.example.repository.RouteDao;
import org.example.service.RouteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;

    @Override
    @Transactional
    public Route insert(Route route) {
        return routeDao.save(route);
    }

    @Override
    public Route findById(long id) {
        if (routeDao.findById(id).isPresent())
            return routeDao.findById(id).get();
        return null;
    }

    @Override
    public Route findByName(String name) {
        return routeDao.findByName(name);
    }

    @Override
    public List<Route> getAll() {
        return routeDao.findAll();
    }

    @Override
    public Route update(Route route) {
        Route newRoute = Route.builder()
                .id(route.getId())
                .name(route.getName())
                .linkPhoto(route.getLinkPhoto())
                .build();
        return routeDao.save(newRoute);
    }

    @Override
    public void deleteById(long id) {
        routeDao.deleteById(id);
    }
}
