package dev.adrianoazevedo.movielist.controllers;

import java.net.URI;
import java.util.List;

import dev.adrianoazevedo.movielist.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.adrianoazevedo.movielist.dto.MovieListDTO;
import dev.adrianoazevedo.movielist.dto.MovieMinDTO;
import dev.adrianoazevedo.movielist.dto.ReplacementDTO;
import dev.adrianoazevedo.movielist.services.MovieListService;
import dev.adrianoazevedo.movielist.services.MovieService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping
public class MovieListController {

    @Autowired
    private MovieListService movieListService;

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/lists")
    public List<MovieListDTO> findAll() {
        List<MovieListDTO> result = movieListService.findAll();
        return result;
    }

    @GetMapping(value = "/list/{id}")
    public MovieListDTO findById(@PathVariable Long id) {
        MovieListDTO result = movieListService.findById(id);
        return result;
    }

    @PostMapping(value = "/list")
    public ResponseEntity<MovieListDTO> insert(@RequestBody MovieListDTO dto) {
        dto = movieListService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/list/{id}")
    public ResponseEntity<MovieListDTO> update(@PathVariable Long id, @RequestBody MovieListDTO dto) {
        dto = movieListService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/list/{id}")
    public ResponseEntity<MovieListDTO> delete(@PathVariable Long id) {
        movieListService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping(value = "/list/{listId}/movies")
    public List<MovieMinDTO> findMovies(@PathVariable Long listId) {
        List<MovieMinDTO> result = movieService.findByMovieList(listId);
        return result;
    }

    @PostMapping(value = "/list/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        movieListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }
}
