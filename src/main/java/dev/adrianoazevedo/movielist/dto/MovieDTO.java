package dev.adrianoazevedo.movielist.dto;

import dev.adrianoazevedo.movielist.entities.Movie;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MovieDTO {

    private static final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    private Long id;
    private String title;
    private Double score;
    private Integer rating;
    private String image;

    public MovieDTO(Long id, String title, Double score, Integer rating, String image) {
        this.id = id;
        this.title = title;
        this.score = Double.valueOf(df.format(score));
        this.rating = rating;
        this.image = image;
    }

    public MovieDTO(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getScore(), movie.getRating(), movie.getImage());
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

    public Integer getRating() {
        return rating;
    }

    public String getImage() {
        return image;
    }


}
