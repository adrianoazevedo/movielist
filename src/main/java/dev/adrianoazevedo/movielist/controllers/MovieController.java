package dev.adrianoazevedo.movielist.controllers;

import dev.adrianoazevedo.movielist.dto.MovieDTO;
import dev.adrianoazevedo.movielist.dto.MovieMinDTO;
import dev.adrianoazevedo.movielist.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;



@RestController
@RequestMapping
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/movies")
    public List<MovieMinDTO> findAll() {
        List<MovieMinDTO> result = movieService.findAll();
        return result;
    }

    /*@GetMapping(value = "/movie/{id}")
    public MovieDTO findById(@PathVariable Long id) {
        MovieDTO result = movieService.findById(id);
        return result;
    }*/

    @GetMapping(value = "/movies/{id}")
    public MovieDTO findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping(value = "/movies")
    public ResponseEntity<MovieDTO> insert(@RequestBody MovieDTO dto) {
        dto = movieService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/movies/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto) {
        dto = movieService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/movies/{id}")
    public ResponseEntity<MovieDTO> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
