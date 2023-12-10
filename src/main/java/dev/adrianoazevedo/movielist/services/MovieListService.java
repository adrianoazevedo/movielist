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

import dev.adrianoazevedo.movielist.dto.MovieListDTO;
import dev.adrianoazevedo.movielist.entities.MovieList;
import dev.adrianoazevedo.movielist.projections.MovieMinProjection;
import dev.adrianoazevedo.movielist.repositories.MovieListRepository;
import dev.adrianoazevedo.movielist.repositories.MovieRepository;

@Service
public class MovieListService {

    @Autowired
    private MovieListRepository movieListRepository;

    @Autowired
    private MovieRepository movieRepository;


    @Transactional(readOnly = true)
    public List<MovieListDTO> findAll() {
        List<MovieList> result = movieListRepository.findAll();
        return result.stream().map(MovieListDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public MovieListDTO findById(Long id) {
        MovieList entity = movieListRepository.findById(id).get();
        return new MovieListDTO(entity);
    }

    @Transactional
    public MovieListDTO insert(MovieListDTO dto) {
        MovieList entity = new MovieList();
        copyDtoToEntity(dto, entity);
        entity = movieListRepository.save(entity);
        return new MovieListDTO(entity);
    }

    @Transactional
    public MovieListDTO update(Long id, MovieListDTO dto) {
        try {
            MovieList entity = movieListRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = movieListRepository.save(entity);
            return new MovieListDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            movieListRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(MovieListDTO dto, MovieList entity) {
        entity.setName(dto.getName());
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {

        List<MovieMinProjection> list = movieRepository.searchByList(listId);

        MovieMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        for (int i = min; i <= max; i++) {
            movieListRepository.updateBelongPosition(listId, list.get(i).getID(), i);
        }
    }



}
