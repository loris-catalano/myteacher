package it.unisannio.gruppo3.student.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.gruppo3.entities.Student;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {
    private static final String BASE_URI = "http://localhost:8081/student/studentService";

    static boolean init = false;

    static Long st1Id;
    static Student st1;

    @BeforeAll
    public static void setup() {
        if (!init) {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                st1 = new Student("Francesco", "Fuccio", 0, null, null, "fra@gmail.com", "1981288");

                ObjectMapper mapper = new ObjectMapper();
                String jsonBody = mapper.writeValueAsString(st1);

                HttpPost request = new HttpPost(BASE_URI);
                StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
                request.setEntity(entity);

                HttpResponse response = httpClient.execute(request);
                assertEquals(201, response.getStatusLine().getStatusCode());

                Header locationHeader = response.getFirstHeader("Location");
                assertNotNull(locationHeader);
                String location = locationHeader.getValue();


                String[] segments = location.split("/");
                st1Id = Long.parseLong(segments[segments.length - 1]);


                assertNotNull(st1Id);
                st1.setId(st1Id);


                init = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    @Order(1)
    public void createStudent() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            Student newStd = new Student("Michele", "Catalano", 0, null, null, "fra@gmail.com", "1981288");

            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(newStd);

            HttpPost request = new HttpPost(BASE_URI);
            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            assertEquals(201, response.getStatusLine().getStatusCode());

            Header locationHeader = response.getFirstHeader("Location");
            assertNotNull(locationHeader);
            String location = locationHeader.getValue();


            String[] segments = location.split("/");
            Long newStdId = Long.parseLong(segments[segments.length - 1]);

            assertNotNull(newStdId);
            newStd.setId(newStdId);

            assertNotEquals(newStdId, st1Id);
            System.out.println(st1Id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(2)
    public void getStudent() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri = new URIBuilder(BASE_URI + "/" + st1Id)
                    .build();

            HttpGet request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);

            assertEquals(200, response.getStatusLine().getStatusCode());

            ObjectMapper mapper = new ObjectMapper();
            Student student = mapper.readValue(EntityUtils.toString(response.getEntity()), Student.class);
            assertEquals(student, st1);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(3)
    public void testGetAllStudents() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(BASE_URI);
            HttpResponse response = httpClient.execute(request);
            assertEquals(200, response.getStatusLine().getStatusCode());
            // Puoi aggiungere ulteriori asserzioni per verificare il corpo della risposta se necessario
        }
    }

    @Test
    @Order(4)
    public void testUpdateStudent() throws Exception {

        st1.setFirstName("Loris");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri = new URIBuilder(BASE_URI).build();

            HttpPut request = new HttpPut(uri);

            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(st1);

            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            assertEquals(200, response.getStatusLine().getStatusCode());
        }
    }

    @Test
    @Order(5)
    public void testDeleteStudent() throws Exception {
        // Assicurati di avere un ID valido per uno studente esistente da eliminare


        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Eseguire una richiesta DELETE per eliminare lo studente
            HttpDelete request = new HttpDelete(BASE_URI + "/" + st1Id);
            HttpResponse response = httpClient.execute(request);
            assertEquals(204, response.getStatusLine().getStatusCode());
        }


    }
}

