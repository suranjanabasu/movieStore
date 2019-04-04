package movie.service;

import movie.entity.Movie;
import movie.repo.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Before
    public void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test public void retrieveMoviesSuccessTest() {
        Movie movie = new Movie("Forrest Gump", "Drama", "2018", 10);
        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        when (movieRepository.findAll()).thenReturn(movieList);
        List<Movie> retrievedMovieList = movieService.retrieveMovies();
        Assert.assertEquals(1, retrievedMovieList.size());
    }

    @Test public void retrieveMoviesWithNothingReturnedTest() {
        when (movieRepository.findAll()).thenReturn(null);
        List<Movie> retrievedMovieList = movieService.retrieveMovies();
        Assert.assertEquals(null,retrievedMovieList);
    }

    @Test
    public void getMovieSuccessTest(){
        Movie movie = new Movie("Forrest Gump", "Drama", "2018", 10);
        when (movieRepository.findById(1l)).
                thenReturn(Optional.of(movie));
        Assert.assertEquals(movie, movieService.getMovie(1l));

    }

    //todo: Similarly implement tests for all other apis
}
