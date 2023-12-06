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


    public String getName() {
        return name;
    }

}
