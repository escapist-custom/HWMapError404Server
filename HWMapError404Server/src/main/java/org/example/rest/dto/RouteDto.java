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

    private String nameOfRoute;

    private String linkPhoto;
    private String description;
    private String km;
    private String time;

    public static Route FromDtoToRoute(RouteDto routeDto, List<User> userList) {
        return Route.builder()
                .id(routeDto.getId())
                .name(routeDto.getNameOfRoute())
                .linkPhoto(routeDto.getLinkPhoto())
                .description(routeDto.getDescription())
                .km(routeDto.getKm())
                .time(routeDto.getTime())
                .build();
    }

    public static RouteDto toDto(Route route) {
        return RouteDto.builder()
                .id(route.getId())
                .nameOfRoute(route.getName())
                .linkPhoto(route.getLinkPhoto())
                .description(route.getDescription())
                .km(route.getKm())
                .time(route.getTime())
                .build();
    }
}
