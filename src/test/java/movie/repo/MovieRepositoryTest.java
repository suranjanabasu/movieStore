package movie.repo;

import movie.entity.Movie;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore ("Class coonflic with swagger. Need to fix this")
@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void addAndFindMovie() {
        Movie testMovie = new Movie("Forrest Gump", "Drama", "2018", 10);
        testEntityManager.persist(testMovie);
        testEntityManager.flush();

        Movie foundMovie = movieRepository.findAll().get(0);
        Assert.assertEquals(foundMovie.getName(), "testname");
    }
}
