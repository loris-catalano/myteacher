package it.unisannio.gruppo3.student.persistence;

import it.unisannio.gruppo3.entities.Student;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class StudentDAOMongoTest {
    static boolean init=false;
    static StudentDAO studentDAO;
    static Long st1Id;
    static Student st1;

    @BeforeAll
    public static void setup(){
        if(!init) {
            studentDAO = new StudentDAOMongo();
            st1Id = studentDAO.getNextId();
            assertTrue(st1Id > 0);
            st1 = new Student(st1Id, "Francesco","Fuccio",0,null,null,"fra@gmail.com","1981288");
            Long studentId = studentDAO.createStudent(st1);
            assertEquals(studentId, st1Id);
            init = true;
        }
    }

    @Test
    void createStudent() {
        Long nextId = studentDAO.getNextId();
        assertTrue(nextId > 0);
        assertEquals(nextId, st1Id);
        Long studentId = studentDAO.createStudent(st1);
        assertEquals(studentId, st1Id);
        System.out.println("nextId = " + nextId);
    }

    @Test
    void getStudent() {
        Student student = studentDAO.getStudent(st1Id);
        assertEquals(st1,student);
    }


    @Test
    void getAllStudents() {
        ArrayList<Student> allStudents = studentDAO.getAllStudents();
        assertFalse(allStudents.isEmpty());
    }

    @Test
    void updateStudent() {
        st1.setFirstName("Loris");
        Student student = studentDAO.updateStudent(st1);
        assertEquals(st1, student);
    }

    @Test
    void deleteStudent() {
        studentDAO.deleteStudent(st1Id);
    }
}
