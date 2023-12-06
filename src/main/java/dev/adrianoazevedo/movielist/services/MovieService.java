package dev.adrianoazevedo.movielist.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.adrianoazevedo.movielist.dto.MovieDTO;
import dev.adrianoazevedo.movielist.dto.MovieMinDTO;
import dev.adrianoazevedo.movielist.entities.Movie;
import dev.adrianoazevedo.movielist.repositories.MovieRepository;
import dev.adrianoazevedo.movielist.projections.MovieMinProjection;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<MovieMinDTO> findAll() {
        List<Movie> result = movieRepository.findAll();
        return result.stream().map(MovieMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(@PathVariable Long listId) {
        Movie result = movieRepository.findById(listId).get();
        return new MovieDTO(result);
    }

    @Transactional(readOnly = true)
    public List<MovieMinDTO> findByMovieList(Long listId) {
        List<MovieMinProjection> movies = movieRepository.searchByList(listId);
        return movies.stream().map(MovieMinDTO::new).toList();
    }
}
