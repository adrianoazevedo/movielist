package dev.adrianoazevedo.movielist.services;

import java.util.List;


import dev.adrianoazevedo.movielist.services.exceptions.DatabaseException;
import dev.adrianoazevedo.movielist.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    /*@Transactional(readOnly = true)
    public MovieDTO findById(@PathVariable Long listId) {
        Movie result = movieRepository.findById(listId).get();
        return new MovieDTO(result);
    }*/

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Movie result = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new MovieDTO(result);
    }

    @Transactional
    public MovieDTO insert(MovieDTO dto) {
        Movie entity = new Movie();
        copyDtoToEntity(dto, entity);
        entity = movieRepository.save(entity);
        return new MovieDTO(entity);
    }

    @Transactional
    public MovieDTO update(Long id, MovieDTO dto) {
        try {
            Movie entity = movieRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = movieRepository.save(entity);
            return new MovieDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            movieRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(MovieDTO dto, Movie entity) {
        entity.setTitle(dto.getTitle());
        entity.setScore(dto.getScore());
        entity.setRating(dto.getRating());
        entity.setImage(dto.getImage());
    }

    @Transactional(readOnly = true)
    public List<MovieMinDTO> findByMovieList(Long listId) {
        List<MovieMinProjection> movies = movieRepository.searchByList(listId);
        return movies.stream().map(MovieMinDTO::new).toList();
    }
}
