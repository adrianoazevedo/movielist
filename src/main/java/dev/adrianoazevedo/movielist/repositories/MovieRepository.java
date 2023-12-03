package dev.adrianoazevedo.movielist.repositories;

import dev.adrianoazevedo.movielist.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
