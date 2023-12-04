package dev.adrianoazevedo.movielist.repositories;

import dev.adrianoazevedo.movielist.entities.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MovieListRepository extends JpaRepository<MovieList, Long> {

    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE tb_belong SET position = :newPosition WHERE list_id = :listId AND movie_id = :movieId")
    void updateBelongPosition(Long listId, Long movieId, Integer newPosition);
}
