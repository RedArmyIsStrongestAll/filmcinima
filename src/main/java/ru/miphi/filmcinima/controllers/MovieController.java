package ru.miphi.filmcinima.controllers;


import org.springframework.web.bind.annotation.*;
import ru.miphi.filmcinima.dto.Movie;
import ru.miphi.filmcinima.dto.MovieWitoutId;
import ru.miphi.filmcinima.services.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<Movie> getAll() {
        return service.getAll();
    }

    @GetMapping("/recent")
    public List<Movie> getRecent() {
        return service.getRecent(3);
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable Long id) {
        Movie movie = service.getById(id);
        if (movie == null) {
            throw new RuntimeException("Movie not found with id " + id);
        }
        return movie;
    }

    @PostMapping
    public Movie add(@RequestBody MovieWitoutId movie) {
        return service.addMovie(new Movie(
                0L,
                movie.getTitle(),
                movie.getDirector(),
                movie.getYear(),
                movie.getGenre(),
                movie.getRating()
        ));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boolean removed = service.deleteMovie(id);
        if (!removed) {
            throw new RuntimeException("Movie not found to delete with id " + id);
        }
    }
}
