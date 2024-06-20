package it.unisannio.gruppo3.lesson.presentation;

import it.unisannio.gruppo3.entities.Lesson;
import it.unisannio.gruppo3.lesson.Presentation.LessonService;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LessonServiceTest {
    private static final String BASE_URI = "http://localhost:8081/lesson/lessonService";

    static LessonService lessonService;
    static boolean init = false;

    static Long le1Id;
    static Lesson le1;

    @BeforeAll
    public static void setup() throws IOException {
        if (!init) {
            lessonService = new LessonService();

            le1 = new Lesson(le1Id, 70,"basiDiDati",0L,null,3,0L);
            // Chiama il metodo createLesson di LessonService per inserire lo lessone
            Response response = lessonService.createLesson(le1);

            // Verifica che la chiamata al metodo createLesson abbia avuto successo (status code 201)
            assertEquals(201, response.getStatus());

            // Estrai l'ID dello lessone dalla risposta
            String location = response.getLocation().toString();
            String[] segments = location.split("/");
            le1Id = Long.parseLong(segments[segments.length - 1]);

            System.out.println(le1Id);
            assertNotNull(le1Id);
            le1.setId(le1Id);

            init = true;
        }
    }

    @Test
    @Order(1)
    public void createLesson() {

        // Creazione di un nuovo lessone
        Lesson leCreated = new Lesson(le1Id, 70,"basiDiDati",0L,null,3,0L);
        Response responseNewLesson = lessonService.createLesson(leCreated);

        // Verifica che la chiamata al metodo createLesson abbia avuto successo (status code 201)
        assertEquals(201, responseNewLesson.getStatus());

        // Estrazione dell'ID del nuovo lessone dalla risposta
        String locationNewLesson = responseNewLesson.getLocation().toString();
        String[] segmentsNewLesson = locationNewLesson.split("/");
        Long newLessonId = Long.parseLong(segmentsNewLesson[segmentsNewLesson.length - 1]);

        // Verifica che l'ID del nuovo lessone sia diverso dall'ID dello lessone le1
        assertNotEquals(newLessonId, le1Id);

        // Riporta il db allo stato iniziale
        lessonService.deleteLesson(newLessonId);
    }
    @Test
    @Order(2)
    public void getLesson() {
        // Chiamata al metodo getLesson per ottenere lo lessone le1
        Response response = lessonService.getLesson(le1Id);

        // Verifica che la chiamata al metodo getLesson abbia avuto successo (status code 200)
        //assertEquals(200, response.getStatus());

        // Estrazione dello lessone dalla risposta
        Lesson lesson = (Lesson) response.getEntity();

        // Verifica che lo lessone ottenuto sia uguale a le1
        assertEquals(le1, lesson);
    }

    @Test
    @Order(3)
    public void testGetAllLessons() {
        // Chiamata al metodo getAllLessons per ottenere tutti gli lessoni
        Response response = lessonService.getAllLessons();

        // Verifica che la chiamata al metodo getAllLessons abbia avuto successo (status code 200)
        assertEquals(200, response.getStatus());

    }

    @Test
    @Order(4)
    public void testUpdateLesson() {
        // Modifica lo lessone le1
        le1.setPrice(50);

        // Chiamata al metodo updateLesson per aggiornare lo lessone le1
        Response response = lessonService.updateLesson(le1);

        // Verifica che la chiamata al metodo updateLesson abbia avuto successo (status code 200)
        assertEquals(200, response.getStatus());
    }

    @Test
    @Order(5)
    public void testDeleteLesson() {
        // Chiamata al metodo deleteLesson per eliminare lo lessone le1
        Response response = lessonService.deleteLesson(le1Id);

        // Verifica che la chiamata al metodo deleteLesson abbia avuto successo (status code 204)
        assertEquals(204, response.getStatus());


    }
}

