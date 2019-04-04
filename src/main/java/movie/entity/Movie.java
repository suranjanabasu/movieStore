package movie.entity;

import javax.persistence.*;


@Entity
@Table(name="MOVIE")
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="genre")
    private String genre;
    @Column(name = "year")
    private String year;
    @Column(name = "rating")
    private Integer rating;

    public Movie() {

    }

    public Movie(String name, String genre, String year, int rating) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.rating = rating;

    }

    public Movie(Long id, String name, String genre, String year, int rating) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.rating = rating;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}

