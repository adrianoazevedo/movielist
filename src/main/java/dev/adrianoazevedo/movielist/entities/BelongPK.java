package dev.adrianoazevedo.movielist.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable
public class BelongPK {

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private MovieList list;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieList getList() {
        return list;
    }

    public void setList(MovieList list) {
        this.list = list;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, list);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BelongPK other = (BelongPK) obj;
        return Objects.equals(movie, other.movie) && Objects.equals(list, other.list);
    }
}
