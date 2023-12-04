package dev.adrianoazevedo.movielist.projections;

public interface MovieMinProjection {

    Long getID();
    String getTitle();
    Double getScore();
    Integer getRating();
    String getImage();
    Integer getPosition();
}
