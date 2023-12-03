package dev.adrianoazevedo.movielist.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_belong")
public class Belong {

    @EmbeddedId
    private BelongPK id = new BelongPK();

    private Integer position;

    public void setMovie(Movie movie) {
        id.setMovie(movie);
    }

    public Movie getMovie() {
        return id.getMovie();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Belong belong)) return false;
        return Objects.equals(id, belong.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


