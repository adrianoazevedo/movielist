package dev.adrianoazevedo.movielist.dto;

import dev.adrianoazevedo.movielist.entities.Movie;
import dev.adrianoazevedo.movielist.projections.MovieMinProjection;

public class MovieMinDTO {

    private Long id;
    private String title;
    private Double score;
    private String image;

    public MovieMinDTO(Movie entity){
        id = entity.getId();
        title = entity.getTitle();
        score = entity.getScore();
        image = entity.getImage();
    }

    public MovieMinDTO(MovieMinProjection projection){
        id = projection.getID();
        title = projection.getTitle();
        score = projection.getScore();
        image = projection.getImage();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getScore() {
        return score;
    }

    public String getImage() {
        return image;
    }
}
