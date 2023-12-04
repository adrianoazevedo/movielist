package dev.adrianoazevedo.movielist.dto;

import dev.adrianoazevedo.movielist.entities.MovieList;

public class MovieListDTO {

    private Long id;
    private String name;

    public MovieListDTO(MovieList entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
