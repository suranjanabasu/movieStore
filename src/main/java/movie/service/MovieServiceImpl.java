package movie.service;

import movie.entity.Movie;
import movie.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> retrieveMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    @Override
    public Movie getMovie(Long movieId) {
        if (movieId != null) {
            Optional<Movie> optionalMovie = movieRepository.findById(movieId);
            return optionalMovie.get();
        }

        return null;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);

    }

    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}
