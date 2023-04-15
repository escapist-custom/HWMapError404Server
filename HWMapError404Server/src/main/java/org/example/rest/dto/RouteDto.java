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

    public static Route FromDtoToRoute(RouteDto routeDto, List<User> userList) {
        return Route.builder()
                .id(routeDto.getId())
                .name(routeDto.getNameOfRoute())
                .linkPhoto(routeDto.getLinkPhoto())
                .build();
    }

    public static RouteDto toDto(Route route) {
        return new RouteDto(
                route.getId(),
                route.getName(),
                route.getLinkPhoto()
        );
    }
}
