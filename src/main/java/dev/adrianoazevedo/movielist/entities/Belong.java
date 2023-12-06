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

    public void setList(MovieList list) {
        id.setList(list);
    }

    public MovieList getList() {
        return id.getList();
    }


    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Belong other = (Belong) obj;
        return Objects.equals(id, other.id);
    }
}


