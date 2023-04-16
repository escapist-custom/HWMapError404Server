package org.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.Route;
import org.example.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;

    private String nickName;

    private String email;

    private String password;

    private List<RouteDto> favoriteRoutes;

    public static UserDto toDto(User user) {
        List<RouteDto> userDtoRoutes;
        if (user.getRoute() != null) {
            userDtoRoutes = user.getRoute().stream().map(RouteDto::toDto).collect(Collectors.toList());
        } else {
            userDtoRoutes = new ArrayList<>();
        }

        return new UserDto(
                user.getId(),
                user.getNickName(),
                user.getEmail(),
                user.getPassword(),
                userDtoRoutes
        );
    }

    public static User FromDtoToUser(UserDto userDto, List<Route> routeList) {
        return new User(
                userDto.getId(),
                userDto.getNickName(),
                userDto.getEmail(),
                userDto.getPassword(),
                routeList
        );
    }
}
