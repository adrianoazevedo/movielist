package dev.adrianoazevedo.movielist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.adrianoazevedo.movielist.dto.MovieListDTO;
import dev.adrianoazevedo.movielist.dto.MovieMinDTO;
import dev.adrianoazevedo.movielist.dto.ReplacementDTO;
import dev.adrianoazevedo.movielist.services.MovieListService;
import dev.adrianoazevedo.movielist.services.MovieService;


@RestController
@RequestMapping(value = "/lists")
public class MovieListController {

    @Autowired
    private MovieListService movieListService;

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/{id}")
    public MovieListDTO findById(@PathVariable Long id) {
        MovieListDTO result = movieListService.findById(id);
        return result;
    }

    @GetMapping
    public List<MovieListDTO> findAll() {
        List<MovieListDTO> result = movieListService.findAll();
        return result;
    }

    @GetMapping(value = "/{listId}/games")
    public List<MovieMinDTO> findGames(@PathVariable Long listId) {
        List<MovieMinDTO> result = movieService.findByGameList(listId);
        return result;
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        movieListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }
}
