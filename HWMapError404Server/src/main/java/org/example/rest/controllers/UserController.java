package org.example.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.example.domain.Route;
import org.example.domain.User;
import org.example.rest.dto.UserDto;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public UserDto createNewUser(
            @RequestParam String nickName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam List<Route> routes
    ) {
        User user = userService.insert(new User(nickName, email, password, routes));
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

    @GetMapping("/user/email")
    public UserDto getUserByEmail(@PathVariable String email) {
        return UserDto.toDto(userService.findByEmail(email));
    }

    @PostMapping("/user/{id}")
    public UserDto updateUserById(
            @PathVariable long id,
            @RequestParam String nickName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam List<Route> routes
    ) {
        User user = userService.update(new User(
                id, nickName, email, password, routes
        ));

        return UserDto.toDto(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteBookById(@PathVariable long id) {
        userService.deleteById(id);
    }
}
