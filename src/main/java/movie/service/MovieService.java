package movie.service;

import movie.entity.Movie;

import java.util.List;

public interface MovieService {
    public List<Movie> retrieveMovies();

    public Movie getMovie(Long movieId);

    public Movie saveMovie(Movie movie);

    public void deleteMovie(Long movieId);

    public void updateMovie(Movie movie);
}
