package dev.adrianoazevedo.movielist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.adrianoazevedo.movielist.dto.MovieListDTO;
import dev.adrianoazevedo.movielist.entities.MovieList;
import dev.adrianoazevedo.movielist.projections.MovieMinProjection;
import dev.adrianoazevedo.movielist.repositories.MovieListRepository;
import dev.adrianoazevedo.movielist.repositories.MovieRepository;


import java.util.List;

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

    @Transactional(readOnly = true)
    public MovieListDTO findById(Long id) {
        MovieList entity = movieListRepository.findById(id).get();
        return new MovieListDTO(entity);
    }

}
