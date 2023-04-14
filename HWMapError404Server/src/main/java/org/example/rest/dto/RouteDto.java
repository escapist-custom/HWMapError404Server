package org.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.Route;
import org.example.domain.User;
import org.example.repository.RouteDao;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {

    private long id;

    private String name;

    private List<UserDto> routeUsers;

    private double[] places;

    public static Route FromDtoToRoute(RouteDto routeDto, List<User> userList) {
        return Route.builder()
                .id(routeDto.getId())
                .name(routeDto.getName())
                .routeUsers(userList)
                .places(routeDto.getPlaces())
                .build();
    }

    public static RouteDto toDto(Route route) {
        List<UserDto> routeDtoUsers;
        if (route.getRouteUsers() != null) {
            routeDtoUsers = route.getRouteUsers().stream().map(UserDto::toDto).collect(Collectors.toList());
        } else {
            routeDtoUsers = new ArrayList<>();
        }
        return new RouteDto(
                route.getId(),
                route.getName(),
                routeDtoUsers,
                route.getPlaces()
        );
    }
}
