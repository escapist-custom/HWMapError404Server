package org.example.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.example.domain.Route;
import org.example.domain.User;
import org.example.rest.dto.RouteDto;
import org.example.rest.dto.UserDto;
import org.example.service.RouteService;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RouteService routeService;

    @PostMapping("/user")
    public UserDto createNewUser(
            @RequestParam String nickName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam List<Route> routes
    ) {
        User user = userService.insert(User.builder()
                .nickName(nickName)
                .email(email)
                .password(password)
                .route(routes)
                .build()
        );
        return UserDto.toDto(user);
    }

    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return userService
                .getAll()
                .stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable long id) {
        return UserDto.toDto(userService.findById(id));
    }

    @PostMapping("/user/{id}")
    public UserDto updateUserById(
            @PathVariable long id,
            @RequestParam String nickName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam List<Route> routes
    ) {
        User user = userService.update(User.builder()
                        .id(id)
                        .nickName(nickName)
                        .email(email)
                        .password(password)
                        .route(routes)
                        .build()
        );
        return UserDto.toDto(user);
    }

    @PatchMapping("add/user/{userId}/route/{routeId}")
    public User addRoute(@PathVariable long userId, @PathVariable long routeId) {
        User user = userService.findById(userId);
        Route route = routeService.findById(routeId);
        List<Route> routes = user.getRoute();
        routes.add(route);
        user.setRoute(routes);
        userService.update(user);
        return user;
    }

    @GetMapping("user/favoriteRoutes/{id}")
    public List<RouteDto> getFavoriteRoutes(@PathVariable long id) {
        List<Route> routes = userService.getFavoriteRoutes(id);
        List<RouteDto> routeDtos = new ArrayList<>();
        for (Route route: routes) {
            RouteDto routeDto = RouteDto.toDto(route);
            routeDtos.add(routeDto);
        }
        return routeDtos;
    }

    @DeleteMapping("/user/{id}")
    public void deleteBookById(@PathVariable long id) {
        userService.deleteById(id);
    }
}
