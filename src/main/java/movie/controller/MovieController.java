package movie.controller;

import movie.entity.Movie;
import movie.exception.BadMovieRequestException;
import movie.exception.MovieNotFoundException;
import movie.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class MovieController {
    Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieService movieService;


    @RequestMapping("/")
    public String index() {
        return "Hi from MovieController.java";
    }

    @GetMapping("/api/movie/list")
    public List<Movie> getMovies() {
        logger.info("Entering getMovies");
        List<Movie> movies = movieService.retrieveMovies();
        return movies;
    }

    @GetMapping("/api/movie/{movieId}")
    public Movie getMovie(@PathVariable(name="movieId")Long movieId) {
        logger.info("Entering getMovie");
        if(movieId == null) {
            throw new MovieNotFoundException(movieId);
        }
        return movieService.getMovie(movieId);
    }

    @PostMapping("/api/movie")
    public ResponseEntity<Object> saveMovie(@RequestBody  Movie movie){
        logger.info("Entering saveMovie");
        if(movie == null) {
            throw new BadMovieRequestException();
        }
        DateFormat format = new SimpleDateFormat("YYYY");
        try {
            format.parse(movie.getYear());
        } catch (ParseException e) {
            System.out.println("Movie year " + movie.getYear() + " is not valid according to " +
                    ((SimpleDateFormat) format).toPattern() + " pattern.");
            throw new BadMovieRequestException();
        }
        Movie savedMovie  = movieService.saveMovie(movie);
        System.out.println("Movie Saved Successfully");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMovie.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    /*THE Call to delete an item in repo is just for illustration
    * I would never delete any data in database but set a column called state
    * i.e mark the data as deleted without actually deleting from a system of record*/

    @DeleteMapping("/api/movie/{movieId}")
    public void deleteMovie(@PathVariable(name="movieId")Long movieId) {
        logger.info("Entering deleteMovie");
        if(movieId == null) {
            throw new MovieNotFoundException(movieId);
        }
        movieService.deleteMovie(movieId);
        System.out.println("Movie Deleted Successfully");
    }

    @PutMapping("/api/movie/{movieId}")
    public void updateMovie(@RequestBody Movie movie,
                               @PathVariable(name="movieId")Long movieId){
        logger.info("Entering updateMovie");
        if(movie == null || movieId == null) {
            throw new MovieNotFoundException(movieId);
        }
        //Validate the year - if year is not valid throw a Bad Request
        DateFormat format = new SimpleDateFormat("YYYY");
        try {
            format.parse(movie.getYear());
        } catch (ParseException e) {
            System.out.println("Movie year " + movie.getYear() + " is not valid according to " +
                    ((SimpleDateFormat) format).toPattern() + " pattern.");
            throw new BadMovieRequestException();
        }

        Movie retrievedMovie = movieService.getMovie(movieId);
        if(retrievedMovie != null){
            movieService.updateMovie(movie);
        } else {
            movieService.saveMovie(movie);
        }

    }

    @RequestMapping("/api/timeOfDay")
    public String timeOfDay() {
        return new Date().toString();

    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
