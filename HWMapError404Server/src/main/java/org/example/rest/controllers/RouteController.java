package org.example.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.example.domain.Route;
import org.example.domain.User;
import org.example.rest.dto.RouteDto;
import org.example.rest.dto.UserDto;
import org.example.service.RouteService;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @PostMapping("/route")
    public RouteDto createRoute(
            @RequestParam String description,
            @RequestParam String nameOfRoute,
            @RequestParam String linkPhoto
    ) {
        Route route = routeService.insert(Route.builder()
                .description(description)
                .name(nameOfRoute)
                .linkPhoto(linkPhoto)
                .build());
        return RouteDto.toDto(route);
    }

    @GetMapping("/route")
    public List<RouteDto> getAllRoutes() {
        return routeService
                .getAll()
                .stream()
                .map(RouteDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/route/{id}")
    public RouteDto getRouteById(@PathVariable long id) {
        return RouteDto.toDto(routeService.findById(id));
    }

    @PostMapping("/route/{id}")
    public RouteDto updateRouteById(
            @PathVariable long id,
            @RequestParam String newName,
            @RequestParam String linkPhoto
    ) {
        Route route = routeService.update(Route.builder()
                .id(id)
                .name(newName)
                .linkPhoto(linkPhoto)
                .build()
        );

        return RouteDto.toDto(route);
    }

    @DeleteMapping("/route/{id}")
    public void deleteRouteById(@PathVariable long id) {
        routeService.deleteById(id);
    }
}
