package dev.adrianoazevedo.movielist.dto;

import dev.adrianoazevedo.movielist.entities.Movie;
import dev.adrianoazevedo.movielist.entities.MovieList;

public class MovieListDTO {

    private Long id;
    private String name;

    /*public MovieListDTO(MovieList entity) {
        id = entity.getId();
        name = entity.getName();
    }*/

    public MovieListDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MovieListDTO(MovieList movieList) {
        this(movieList.getId(), movieList.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
