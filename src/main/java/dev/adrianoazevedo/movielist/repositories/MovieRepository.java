package dev.adrianoazevedo.movielist.repositories;

import dev.adrianoazevedo.movielist.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
