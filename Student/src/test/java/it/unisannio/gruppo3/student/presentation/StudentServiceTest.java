package it.unisannio.gruppo3.student.presentation;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisannio.gruppo3.entities.Student;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentServiceTest {
    private static final String BASE_URI = "http://localhost:8081/student/studentService";

    static boolean init=false;

    static Long st1Id;
    static Student st1;
    static StudentService studentService;
    @BeforeAll
    public static void setup() {
        if (!init) {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                studentService=new StudentService();

                st1Id=studentService.logic.getNextId();
                assertTrue(st1Id > 0);

                // Creazione dell'oggetto Student
                st1 = new Student(st1Id,"Francesco", "Fuccio", 0, null, null, "fra@gmail.com", "1981288");

                // Trasformazione dell'oggetto Student in JSON
                ObjectMapper mapper = new ObjectMapper();
                String jsonBody = mapper.writeValueAsString(st1);

                // Creazione della richiesta POST
                HttpPost request = new HttpPost(BASE_URI);
                StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
                request.setEntity(entity);

                // Esecuzione della richiesta POST
                HttpResponse response = httpClient.execute(request);

                // Assicurati che la richiesta POST sia andata a buon fine (HTTP 201 Created)
                assertEquals(201, response.getStatusLine().getStatusCode());

                // Estrai l'ID dello studente dalla risposta
                Header locationHeader = response.getFirstHeader("Location");
                assertNotNull(locationHeader);
                String location = locationHeader.getValue();
                String[] segments = location.split("/");
                Long studentId = Long.parseLong(segments[segments.length - 1]);
                // Assert per verificare che lo studentId non sia nullo
                assertEquals(st1Id,studentId);
                init = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void createStudent() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            Long nextId = studentService.logic.getNextId();
            assertTrue(nextId>0);
            assertEquals(nextId, st1Id);
            HttpPost request = new HttpPost(BASE_URI);

            // Trasformazione dell'oggetto Student in JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(st1);

            // Impostazione del corpo della richiesta HTTP
            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            request.setEntity(entity);

            // Esecuzione della richiesta HTTP
            HttpResponse response = httpClient.execute(request);

            // Asserzioni sulla risposta ricevuta
            assertEquals(201, response.getStatusLine().getStatusCode());

            // Estrai l'ID dello studente dalla risposta
            Header locationHeader = response.getFirstHeader("Location");
            assertNotNull(locationHeader);
            String location = locationHeader.getValue();
            String[] segments = location.split("/");
            Long studentId = Long.parseLong(segments[segments.length - 1]);
            assertEquals(studentId,st1Id);
            System.out.println("nextId = " + nextId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void getStudent() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(BASE_URI + "/" + st1Id);

            HttpResponse response = httpClient.execute(request);

            assertEquals(200, response.getStatusLine().getStatusCode());

            // Estrai lo studente dal corpo della risposta
            ObjectMapper mapper = new ObjectMapper();
            Student student = mapper.readValue(EntityUtils.toString(response.getEntity()), Student.class);
            assertEquals(student,st1);
        }
    }









}
