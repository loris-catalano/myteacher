package it.unisannio.gruppo3.teacher.presentation;

import it.unisannio.gruppo3.entities.Teacher;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherServiceTest {
    private static final String BASE_URI = "http://localhost:8081/teacher/teacherService";

    static TeacherService teacherService;
    static boolean init = false;

    static Long th1Id;
    static Teacher th1;

    @BeforeAll
    public static void setup() throws IOException {
        if (!init) {
            teacherService = new TeacherService();

            th1 = new Teacher(th1Id, "Francesco","Fuccio",40,false,null,null,12.0,192.0,null,"basiDiDati",null,"fra@gmail.com","1981288");

            // Chiama il metodo createTeacher di TeacherService per inserire lo teachere
            Response response = teacherService.createTeacher(th1);

            // Verifica che la chiamata al metodo createTeacher abbia avuto successo (status code 201)
            assertEquals(201, response.getStatus());

            // Estrai l'ID dello teachere dalla risposta
            String location = response.getLocation().toString();
            String[] segments = location.split("/");
            th1Id = Long.parseLong(segments[segments.length - 1]);

            System.out.println(th1Id);
            assertNotNull(th1Id);
            th1.setId(th1Id);

            init = true;
        }
    }

    @Test
    @Order(1)
    public void createTeacher() {

        // Creazione di un nuovo teachere
        Teacher thCreated = new Teacher("Michele","Fuccio",40,false,null,null,12.0,192.0,null,"basiDiDati",null,"michele@gmail.com","1981288");
        Response responseNewTeacher = teacherService.createTeacher(thCreated);

        // Verifica che la chiamata al metodo createTeacher abbia avuto successo (status code 201)
        assertEquals(201, responseNewTeacher.getStatus());

        // Estrazione dell'ID del nuovo teachere dalla risposta
        String locationNewTeacher = responseNewTeacher.getLocation().toString();
        String[] segmentsNewTeacher = locationNewTeacher.split("/");
        Long newTeacherId = Long.parseLong(segmentsNewTeacher[segmentsNewTeacher.length - 1]);

        // Verifica che l'ID del nuovo teachere sia diverso dall'ID dello teachere th1
        assertNotEquals(newTeacherId, th1Id);

        // Riporta il db allo stato iniziale
        teacherService.deleteTeacher(newTeacherId);
    }
    @Test
    @Order(2)
    public void getTeacher() {
        // Chiamata al metodo getTeacher per ottenere lo teachere th1
        Response response = teacherService.getTeacher(th1Id);

        // Verifica che la chiamata al metodo getTeacher abbia avuto successo (status code 200)
        //assertEquals(200, response.getStatus());

        // Estrazione dello teachere dalla risposta
        Teacher teacher = (Teacher) response.getEntity();

        // Verifica che lo teachere ottenuto sia uguale a th1
        assertEquals(th1, teacher);
    }

    @Test
    @Order(3)
    public void testGetAllTeachers() {
        // Chiamata al metodo getAllTeachers per ottenere tutti gli teacheri
        Response response = teacherService.getAllTeachers();

        // Verifica che la chiamata al metodo getAllTeachers abbia avuto successo (status code 200)
        assertEquals(200, response.getStatus());

    }

    @Test
    @Order(4)
    public void testUpdateTeacher() {
        // Modifica lo teachere th1
        th1.setFirstName("Loris");

        // Chiamata al metodo updateTeacher per aggiornare lo teachere th1
        Response response = teacherService.updateTeacher(th1);

        // Verifica che la chiamata al metodo updateTeacher abbia avuto successo (status code 200)
        assertEquals(200, response.getStatus());
    }

    @Test
    @Order(5)
    public void testDeleteTeacher() {
        // Chiamata al metodo deleteTeacher per eliminare lo teachere th1
        Response response = teacherService.deleteTeacher(th1Id);

        // Verifica che la chiamata al metodo deleteTeacher abbia avuto successo (status code 204)
        assertEquals(204, response.getStatus());


    }
}

