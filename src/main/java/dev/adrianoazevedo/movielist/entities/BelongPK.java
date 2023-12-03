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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BelongPK belongPK)) return false;
        return Objects.equals(getMovie(), belongPK.getMovie()) && Objects.equals(getList(), belongPK.getList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, list);
    }
}
