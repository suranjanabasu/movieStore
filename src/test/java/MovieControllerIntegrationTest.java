import movie.MainApplicationClass;
import movie.entity.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MainApplicationClass.class)
public class MovieControllerIntegrationTest {
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void tes1tCreateMovie() throws Exception {
       // String input = "{\"name\":\"test\", \"description\":\"desc\"}";
        Movie movie = new Movie("Forrest Gump", "Drama", "1994", 10);
        HttpEntity<Movie> entity = new HttpEntity<>(movie, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/movie"), HttpMethod.POST, entity, String.class);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(statusCode.value(), 201);
    }


    @Test
    public void test2RetrieveMovie() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/movie/1"), HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":1,\"name\":\"Forrest Gump\",\"genre\":\"Drama\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    //Failure Test
    @Test
    public void testCreateMovieWithBadYear() throws Exception {
        // String input = "{\"name\":\"test\", \"description\":\"desc\"}";
        Movie movie = new Movie("Forrest Gump", "Drama", "tyty", 10);
        HttpEntity<Movie> entity = new HttpEntity<>(movie, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/movie"), HttpMethod.POST, entity, String.class);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(statusCode.value(), 400);
    }
    //todo: Add other tests arnd update , delete etc
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
