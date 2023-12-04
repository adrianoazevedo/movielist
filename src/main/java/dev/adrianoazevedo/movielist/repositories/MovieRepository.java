package dev.adrianoazevedo.movielist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.adrianoazevedo.movielist.entities.Movie;
import dev.adrianoazevedo.movielist.projections.MovieMinProjection;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = """
			SELECT tb_movie.id, tb_movie.title, tb_movie.score AS score, tb_movie.rating AS rating,
			tb_movie.image AS image, tb_belong.position
			FROM tb_movie
			INNER JOIN tb_belong ON tb_movie.id = tb_belong.game_id
			WHERE tb_belong.list_id = :listId
			ORDER BY tb_belong.position
				""")
    List<MovieMinProjection> searchByList(Long listId);
}
