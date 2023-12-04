package dev.adrianoazevedo.movielist.controllers;

import dev.adrianoazevedo.movielist.dto.MovieDTO;
import dev.adrianoazevedo.movielist.dto.MovieMinDTO;
import dev.adrianoazevedo.movielist.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieMinDTO> findAll() {
        List<MovieMinDTO> result = movieService.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public MovieDTO findById(@PathVariable Long id) {
        MovieDTO result = movieService.findById(id);
        return result;
    }

}
