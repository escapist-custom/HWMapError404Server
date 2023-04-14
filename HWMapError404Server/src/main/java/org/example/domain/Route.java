package org.example.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    public Route(String name, List<User> routeUsers, double[] places) {
        this.name = name;
        this.routeUsers = routeUsers;
        this.places = places;
    }

    @ManyToMany
    @JoinTable(name = "users_route",
        joinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> routeUsers;

    @Column(name = "places")
    private double[] places;

}
