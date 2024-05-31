package it.unisannio.gruppo3.student.logic;

import it.unisannio.gruppo3.entities.Student;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentLogicImplTest {
    static boolean init=false;
    static StudentLogic studentLogic;
    static Long st1Id;
    static Student st1;

    @BeforeAll
    public static void setup(){
        if(!init) {
            studentLogic = new StudentLogicImpl();
            st1Id = studentLogic.getNextId();
            assertTrue(st1Id > 0);
            st1 = new Student(st1Id, "Francesco","Fuccio",0,null,null,"fra@gmail.com","1981288");
            Long studentId = studentLogic.createStudent(st1);
            assertEquals(studentId, st1Id);
            init = true;
        }
    }


    @Test
    @Order(1)

    void createStudent() {
        Long nextId = studentLogic.getNextId();
        assertTrue(nextId > 0);
        assertEquals(nextId, st1Id+1);
        Student stCreated = new Student(nextId, "Michele","Fuccio",0,null,null,"fra@gmail.com","1981288");
        Long newStudentId = studentLogic.createStudent(stCreated);
        assertEquals(newStudentId, st1Id+1);

    }

    @Test
    @Order(2)

    void getStudent() {
        Student student = studentLogic.getStudent(st1Id);

        assertEquals(st1,student);
    }


    @Test
    @Order(3)
    void getAllStudents() {
        ArrayList<Student> allStudents = studentLogic.getAllStudents();
        assertFalse(allStudents.isEmpty());
    }

    @Test
    @Order(4)
    void updateStudent() {
        st1.setFirstName("Loris");
        Student student = studentLogic.updateStudent(st1);
        assertEquals(st1, student);
    }

    @Test
    @Order(5)
    void deleteStudent() {
        studentLogic.deleteStudent(st1Id);
    }

}
