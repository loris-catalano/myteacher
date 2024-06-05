package it.unisannio.gruppo3.student.presentation;


import it.unisannio.gruppo3.entities.Student;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {
    private static final String BASE_URI = "http://localhost:8081/student/studentService";

    static StudentService studentService;
    static boolean init = false;

    static Long st1Id;
    static Student st1;

   @BeforeAll
   public static void setup() throws IOException {
      if (!init) {
        studentService = new StudentService();

        st1 = new Student("Francesco", "Fuccio", 0, null, null, "fra@gmail.com", "1981288");

        // Chiama il metodo createStudent di StudentService per inserire lo studente
        Response response = studentService.createStudent(st1);

        // Verifica che la chiamata al metodo createStudent abbia avuto successo (status code 201)
        assertEquals(201, response.getStatus());

        // Estrai l'ID dello studente dalla risposta
        String location = response.getLocation().toString();
        String[] segments = location.split("/");
        st1Id = Long.parseLong(segments[segments.length - 1]);

        assertNotNull(st1Id);
        st1.setId(st1Id);

        init = true;
      }
   }

    @Test
    @Order(1)
    public void createStudent() {

        // Creazione di un nuovo studente
        Student newStudent = new Student("Michele", "Catalano", 0, null, null, "michele@gmail.com", "1981288");
        Response responseNewStudent = studentService.createStudent(newStudent);

        // Verifica che la chiamata al metodo createStudent abbia avuto successo (status code 201)
        assertEquals(201, responseNewStudent.getStatus());

        // Estrazione dell'ID del nuovo studente dalla risposta
        String locationNewStudent = responseNewStudent.getLocation().toString();
        String[] segmentsNewStudent = locationNewStudent.split("/");
        Long newStudentId = Long.parseLong(segmentsNewStudent[segmentsNewStudent.length - 1]);

        // Verifica che l'ID del nuovo studente sia diverso dall'ID dello studente st1
        assertNotEquals(newStudentId, st1Id);
    }
    @Test
    @Order(2)
    public void getStudent() {
        // Chiamata al metodo getStudent per ottenere lo studente st1
        Response response = studentService.getStudent(st1Id);

        // Verifica che la chiamata al metodo getStudent abbia avuto successo (status code 200)
        assertEquals(200, response.getStatus());

        // Estrazione dello studente dalla risposta
        Student student = response.readEntity(Student.class);

        // Verifica che lo studente ottenuto sia uguale a st1
        assertEquals(st1, student);
    }

    @Test
    @Order(3)
    public void testGetAllStudents() {
        // Chiamata al metodo getAllStudents per ottenere tutti gli studenti
        Response response = studentService.getAllStudents();

        // Verifica che la chiamata al metodo getAllStudents abbia avuto successo (status code 200)
        assertEquals(200, response.getStatus());



    }

    @Test
    @Order(4)
    public void testUpdateStudent() {
        // Modifica lo studente st1
        st1.setFirstName("Loris");

        // Chiamata al metodo updateStudent per aggiornare lo studente st1
        Response response = studentService.updateStudent(st1);

        // Verifica che la chiamata al metodo updateStudent abbia avuto successo (status code 200)
        assertEquals(200, response.getStatus());
    }

    @Test
    @Order(5)
    public void testDeleteStudent() {
        // Chiamata al metodo deleteStudent per eliminare lo studente st1
        Response response = studentService.deleteStudent(st1Id);

        // Verifica che la chiamata al metodo deleteStudent abbia avuto successo (status code 204)
        assertEquals(204, response.getStatus());
    }
}

