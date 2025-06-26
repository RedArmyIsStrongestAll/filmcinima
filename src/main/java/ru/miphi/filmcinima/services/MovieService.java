package ru.miphi.filmcinima.services;

import org.springframework.stereotype.Service;
import ru.miphi.filmcinima.dto.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final List<Movie> movies = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public MovieService() {
        // Инициализация фильмами Тарковского
        addMovie(new Movie(null, "Андрей Рублёв", "Андрей Тарковский", 1966, "Историческая драма", 8.1));
        addMovie(new Movie(null, "Солярис", "Андрей Тарковский", 1972, "Научная фантастика", 8.0));
        addMovie(new Movie(null, "Зеркало", "Андрей Тарковский", 1975, "Драма", 8.1));
        addMovie(new Movie(null, "Сталкер", "Андрей Тарковский", 1979, "Фантастика", 8.1));
        addMovie(new Movie(null, "Ностальгия", "Андрей Тарковский", 1983, "Драма", 7.9));
        addMovie(new Movie(null, "Жертвоприношение", "Андрей Тарковский", 1986, "Драма", 7.8));
    }

    public List<Movie> getAll() {
        return new ArrayList<>(movies);
    }

    public List<Movie> getRecent(int count) {
        return movies.stream()
                .skip(Math.max(0, movies.size() - count))
                .collect(Collectors.toList());
    }

    public Movie getById(Long id) {
        return movies.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Movie addMovie(Movie movie) {
        movie.setId(idGenerator.incrementAndGet());
        movies.add(movie);
        return movie;
    }

    public boolean deleteMovie(Long id) {
        return movies.removeIf(m -> m.getId().equals(id));
    }
}