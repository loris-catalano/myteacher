package it.unisannio.gruppo3.student.logic;

import it.unisannio.gruppo3.entities.Student;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
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
    void createStudent() {
        Long nextId = studentLogic.getNextId();
        assertTrue(nextId > 0);
        assertEquals(nextId, st1Id);
        Long studentId = studentLogic.createStudent(st1);
        assertEquals(studentId, st1Id);
        System.out.println("nextId = " + nextId);
    }

    @Test
    void getStudent() {
        Student student = studentLogic.getStudent(st1Id);
        assertEquals(st1,student);
    }


    @Test
    void getAllStudents() {
        ArrayList<Student> allStudents = studentLogic.getAllStudents();
        assertFalse(allStudents.isEmpty());
    }

    @Test
    void updateStudent() {
        st1.setFirstName("Loris");
        Student student = studentLogic.updateStudent(st1);
        assertEquals(st1, student);
    }

    @Test
    void deleteStudent() {
        studentLogic.deleteStudent(st1Id);
    }

}
