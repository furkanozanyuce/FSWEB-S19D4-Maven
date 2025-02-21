package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "movie", schema = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Film adı null olamaz")
    private String name;

    @Column(name = "director_name")
    @NotNull(message = "Yönetmen adı null olamaz")
    private String directorName;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_actor", schema = "movies",
            inverseJoinColumns = @JoinColumn(name = "actor_id"),
            joinColumns = @JoinColumn(name = "movie_id"))
    private List<Actor> actors;


    public void addActor(Actor actor){
        if (actors == null) {
            actors = new ArrayList<>();
        }
        actors.add(actor);
    }



}



































